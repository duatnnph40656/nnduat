package nro.models.item;

import nro.consts.ConstItem;

import java.util.ArrayList;
import java.util.List;

public class Item {

    private static final ItemOption OPTION_NULL = new ItemOption(73, 0);

    public ItemTemplate template;

    public String info;

    public String content;

    public int quantity;

    public List<ItemOption> itemOptions;

    public long createTime;

    public boolean isNotNullItem() {
        return this.template != null;
    }

    public Item() {
        this.itemOptions = new ArrayList<>();
        this.createTime = System.currentTimeMillis();
    }

    public String getInfo() {
        String strInfo = "";
        for (ItemOption itemOption : itemOptions) {
            strInfo += itemOption.getOptionString();
        }
        return strInfo;
    }

    public String getInfoItem() {
        String strInfo = "|1|" + template.name + "\n|0|";
        for (ItemOption itemOption : itemOptions) {
            strInfo += itemOption.getOptionString() + "\n";
        }
        strInfo += "|2|" + template.description;
        return strInfo;
    }

    public List<ItemOption> getDisplayOptions() {
        List<ItemOption> list = new ArrayList<>();
        if (itemOptions.isEmpty()) {
            list.add(OPTION_NULL);
        } else {
            for (ItemOption o : itemOptions) {
                list.add(o.format());
            }
        }
        return list;
    }

    public String getContent() {
        return "Yêu cầu sức mạnh " + this.template.strRequire + " trở lên";
    }

    public boolean canConsign() {
        byte type = template.type;
        for (ItemOption o : itemOptions) {
            int optionId = o.optionTemplate.id;
            if (template.id != ConstItem.THOI_VANG && type == 12 || type == 33 || type == 29 || type == 27 || type == 11 || type == 5 || (type < 4 && (optionId == 86 || optionId == 87))) {
                return true;
            }
        }
        return false;
    }

    public void dispose() {
        this.template = null;
        this.info = null;
        this.content = null;
        if (this.itemOptions != null) {
            for (ItemOption io : this.itemOptions) {
                io.dispose();
            }
            this.itemOptions.clear();
        }
        this.itemOptions = null;
    }

    public short getId() {
        return template.id;
    }

    public byte getType() {
        return template.type;
    }

    public String getName() {
        return template.name;
    }

}
