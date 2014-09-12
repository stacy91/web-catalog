package com.helpers;

public enum Roles {

	Admin(3), User(4);

	private int id;

	private Roles(int id) {
		this.id = id;
	}

	public static Roles getType(Integer id) {

		if (id != null) {

			for (Roles role : Roles.values()) {
				if (id.equals(role.getId())) {
					return role;
				}
			}
		}
		
		return null;
	}

	public int getId() {
		return id;
	}
}
