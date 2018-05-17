package pac.library;

public class User {
private int id;
private String name;
private String name2;
public User(int id, String name, String name2) {
	super();
	this.id = id;
	this.name = name;
	this.name2 = name2;
}
public int getId() {
	return id;
}
public String getName() {
	return name;
}
public String getName2() {
	return name2;
}
@Override
public String toString() {
	return "User [id=" + id + ", name=" + name + ", name2=" + name2 + "]";
}


}
