/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.item;

/**
 *
 * @author Kitak
 */
public class ItemTemplate {

    public short id;

    public byte type;

    public byte gender;

    public String name;

    public String description;

    public byte level;

    public short iconID;

    public short part;

    public boolean isUpToUp;

    public int strRequire;

    public int gold;

    public int gem;

    public int ruby;

    public int head;

    public int body;

    public int leg;

    public ItemTemplate() {
    }

    public ItemTemplate(short id, byte type, byte gender, String name, String description, short iconID, short part, boolean isUpToUp, int strRequire) {
        this.id = id;
        this.type = type;
        this.gender = gender;
        this.name = name;
        this.description = description;
        this.iconID = iconID;
        this.part = part;
        this.isUpToUp = isUpToUp;
        this.strRequire = strRequire;
    }
}

