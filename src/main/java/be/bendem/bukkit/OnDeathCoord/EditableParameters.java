package be.bendem.bukkit.ondeathcoord;

import java.util.ArrayList;
import java.util.List;

public enum EditableParameters {
    PUBLIC_DEATH("public-death"),
    SHOW_DESPAWN_TIME("show-despawn-time"),
    REMIND_AT_DESPAWN("remind-at-despawn"),
    PREFIX_PUBLIC_MESSAGES("prefix-public-messages"),
    PREFIX_PRIVATE_MESSAGES("prefix-private-messages");

    private String config_node = "";

    EditableParameters(String config_node){
      this.config_node = config_node;
    }

    public String toString(){
      return config_node;
    }

    public static List<String> stringValues() {
        List<String> list = new ArrayList<String>();
        for (EditableParameters p : values()) {
            list.add(p.toString());
        }
        return list;
    }

    public static boolean contains(String config_node) {
        for (String p : stringValues()) {
            if(p.equalsIgnoreCase(config_node)) {
                return true;
            }
        }
        return false;
    }

}
