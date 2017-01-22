package downtoearth.world.worldGen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NoiseGen {
	public static final int WORK_SIZE = 64;

	private int seed;
	private int octave;
	private int width;
	private int height;
	private double time;

	private ConcurrentHashMap<Point, Image> map;

	private ExecutorService pool;
	private volatile boolean stopsignal;

	public NoiseGen() {
		// Default seed is random
		this((int) (Math.random() * 1000));
	}

	public NoiseGen(int seed) {
		this.seed = seed;
		this.stopsignal = true;
		this.octave = 5;
		this.time = 0;
		
		newPool();
		map = new ConcurrentHashMap<>();
	}

	private void newPool() {
		pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	}

	public Map<Point, Image> getMap() {
		return map;
	}

	public boolean isRunning() {
		return !stopsignal;
	}

	public void start() {
		time = System.nanoTime();
		if (isRunning())
			return;

		stopsignal = false;
		map.clear();

		generate();
	}

	public void stop() {
		stopsignal = true;

		pool.shutdownNow();
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// care
		}
		newPool();
	}

	public void generate() {
		generate(0, 0);
	}

	public void generate(int startX, int startY) {
		startX -= startX % WORK_SIZE;
		startY -= startY % WORK_SIZE;

		// generate from block right of existing workload
		for (int y = 0; y < startY; y += WORK_SIZE) {
			for (int x = startX; x < width; x += WORK_SIZE) {
				if (stopsignal)
					return;

				pool.execute(new Worker(x, y));
			}
		}
		// generate everything below existing workload
		for (int y = startY; y < height; y += WORK_SIZE) {
			for (int x = 0; x < width; x += WORK_SIZE) {
				if (stopsignal)
					return;

				pool.execute(new Worker(x, y));
			}
		}
	}

	public void addToBuffer(int x, int y, Image img) {
		map.put(new Point(x, y), img);
	}

	private class Worker implements Runnable {
		int fromX;
		int fromY;

		public Worker(int fromX, int fromY) {
			this.fromX = fromX;
			this.fromY = fromY;
		}

		@Override
		public void run() {
			BufferedImage buffer = new BufferedImage(WORK_SIZE, WORK_SIZE, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = buffer.createGraphics();
			
			double maxDistance = distance(new Point(0, 0), new Point(width/2, height/2));
			double baseHeight = 0.1;
			double edgePush = 1.1;
			double edgeFallOff = 2.00;
			//double maxDistance = 678.82;
			//System.out.println(maxDistance);
			//System.out.println(new Point(width/2, height/2).toString());
			
			for (int x = 0; x < WORK_SIZE; x++) {
				for (int y = 0; y < WORK_SIZE; y++) {

					if (stopsignal)
						return;

					double noiseValue = noisePoint((fromX + x + seed), (fromY + y + seed));
					noiseValue = (noiseValue + 1) / 2;

					int coordinateX = fromX + x;
					int coordinateY = fromY + y;
					double distance = distance(new Point(coordinateX, coordinateY), new Point(width/2, height/2));
					distance /= maxDistance;
					//System.out.println("X: " + coordinateX + " - Y: " + coordinateY + " - Distance: " + distance);
					noiseValue = (noiseValue + baseHeight) * (1 - edgePush * Math.pow(distance, edgeFallOff));
					//noiseValue = Math.pow(noiseValue - 0.5, 3) + 0.5;
					if (noiseValue < 0) noiseValue = 0;
					//if (noiseValue > 1 ) System.out.println("value was invalid: " + noiseValue + " - Distance: " + distance);
					
					int val = (int) Math.round(noiseValue * 255);
					
					
					g.setColor(calculateColor(val));
					//g.setColor(new Color(val, val, val));
					//if (val == 110) g.setColor(new Color(255, 32, 32));
					g.drawRect(x, y, 1, 1);
				}

			}
			
			addToBuffer(fromX, fromY, buffer);
		}
		
		private double distance(Point pos1, Point pos2) {
			
			return pos1.distance(pos2);
		}

		private double noisePoint(int x, int y) {

			double noiseValue = 0;
			
			double frequency = 0.004;
			double octaveWeight = 0.3;
			double weightMultiplier = 0.2;
			double frequencyMultiplier = 3;
			
			for (int i = 0; i < octave; i++) {
				
				noiseValue += (SimplexNoise.noise(x * frequency, y * frequency)) * octaveWeight;
				
				frequency *= frequencyMultiplier;
				octaveWeight *= weightMultiplier;
				
			}
			return noiseValue;
		}
		
		private Color calculateColor(int val) {
			
			Color color;
			int seaBorder = 95;
			int shallowBorder = 110;
			int shoreBorder = 116;
			int landBorder = 155;
			int hillBorder = 165;
			int mountainBorder = 175;
			int snowBorder = 255;
			
			if (val < seaBorder) {
				color = new Color(20, 90, 156);
				return color;
			}
			else if (val >= seaBorder && val < shallowBorder) {
				color = new Color(36, 119, 170);
				return color;
			}
			else if (val >= shallowBorder && val < shoreBorder) {
				color = new Color(226, 198, 83);
				return color;
			}
			else if (val >= shoreBorder && val < landBorder) {
				color = new Color(95, 150, 40);
				return color;
			}
			else if (val >= landBorder && val < hillBorder) {
				color = new Color(116, 113, 116);
				return color;
			}
			else if (val >= hillBorder && val < mountainBorder) {
				color = new Color(148, 149, 151);
				return color;
			}
			else if (val >= mountainBorder && val < snowBorder) {
				color = new Color(255, 226, 225);
				return color;
			}
			return color = new Color(val, val, val);
			
		}
	}
	
	public void reset() {
		stopsignal = true;
		start();
	}

	public void resized(int newWidth, int newHeight) {
		if (!(newWidth > width || newHeight > height))
			return;

		int oldWidth = width;
		int oldHeight = height;

		width = newWidth;
		height = newHeight;

		generate(oldWidth, oldHeight);
	}
}
