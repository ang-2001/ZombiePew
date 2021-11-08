public abstract class Entity {
	protected String type;
	protected String color;
	protected String notification;
	protected String name;
	protected String status;
	protected String weapon;
	int x;
	int y;
	protected boolean running;
	protected int hitPoints;

	public String getType() {
		return type;
	};

	public void setType(String type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	};

	public void setColor(String color) {
		this.color = color;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean getRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public int getHitPonts() {
		return hitPoints;
	}

	public void setHitPonts(int hitPoints) {
		this.hitPoints = hitPoints;
	}

}
