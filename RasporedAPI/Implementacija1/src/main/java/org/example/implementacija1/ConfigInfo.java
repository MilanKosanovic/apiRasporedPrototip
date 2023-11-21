package org.example.implementacija1;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfigInfo {

    private String condition;

    private String tip;


    public ConfigInfo(String condition,String tip) {
        this.tip = tip;
        this.condition = condition;
    }


    @Override
    public String toString() {
        return "ConfigInfo{" +
                "condition='" + condition + '\'' +
                ", tip='" + tip + '\'' +
                '}';
    }
}
