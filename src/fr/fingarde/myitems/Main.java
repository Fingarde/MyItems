package fr.fingarde.myitems;

import com.mysql.fabric.xmlrpc.base.Array;
import org.bukkit.Color;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class Main extends JavaPlugin
{
    static String toParse = "{\n" +
            "  \t\t\"andagor\": {\n" +
            "\t\t\"name\": \"&eAndagor\",\n" +
            "\t\t\"texture\": \"andagor\",\n" +
            "\t\t\"type\": \"block\",\n" +
            "\t\t\"stack\": 64,\n" +
            "\t\t\"baseitem\": \"diamond_sword\",\n" +
            "\n" +
            "\t\t\"color\": \"#ff0000\"\n" +
            "\t\t\"drops\": [\"stone\" \"andagor\"]\n" +
            "\n" +
            "\t}\n" +
            "}";

    public static void main(String[] ee)
    {
        JSONObject items;

        try
        {
            items = (JSONObject) new JSONParser().parse(toParse);

            for(Object localizedItem : items.keySet())
            {
                if(localizedItem.getClass() != String.class) {/* TODO ERROR */}

                String localizedName = (String) localizedItem;

                JSONObject item = (JSONObject) items.get((localizedItem));

                if(item.get("name").getClass() != String.class) {/* TODO ERROR */}
                if(item.get("texture").getClass() != String.class) {/* TODO ERROR */}

                String name = (String)item.get("name");
                String texture = (String)item.get("texture");

                int maxStackSize = 0;

                if(item.get("stack").getClass() == Long.class)
                {
                    maxStackSize = Math.toIntExact((Long) item.get("stack"));
                }
                else if (item.get("stack").getClass() == String.class)
                {
                    try
                    {
                        maxStackSize = Integer.parseInt((String) item.get("stack"));
                    }
                    catch (NumberFormatException e) {/* TODO ERROR */}
                }
                else {/* TODO ERROR */}

                if(item.get("type").getClass() != String.class) {/* TODO ERROR */}
                MyItem.ItemType itemType = MyItem.ItemType.valueOf(((String) item.get("type")).toUpperCase());

                if(item.get("baseitem").getClass() != String.class) {/* TODO ERROR */}
                MyItem.BaseItem baseItem = MyItem.BaseItem.valueOf(((String) item.get("baseitem")).toUpperCase());

                int durability = 0;

                MyItem myItem = null;
                switch (itemType)
                {
                    case ARMOR:
                        if(item.get("color").getClass() != String.class) {/* TODO ERROR */}

                        String colorStr = (String) item.get("color");

                        if(colorStr.startsWith("#")) colorStr = colorStr.substring(2);
                        if(colorStr.length() < 6) {/* TODO ERROR */}

                        durability = 0;
                        Color color = null;
                        int armor = 0;
                        int armorThougness = 0;

                        if(item.get("armor").getClass() == Long.class)
                        {
                            armor = Math.toIntExact((Long) item.get("armor"));
                        }
                        else if (item.get("armor").getClass() == String.class)
                        {
                            try
                            {
                                armor = Integer.parseInt((String) item.get("armor"));
                            }
                            catch (NumberFormatException e) {/* TODO ERROR */}
                        }
                        else {/* TODO ERROR */}

                        if(item.get("armor").getClass() == Long.class)
                        {
                            armor = Math.toIntExact((Long) item.get("armor"));
                        }
                        else if (item.get("armor").getClass() == String.class)
                        {
                            try
                            {
                                armor = Integer.parseInt((String) item.get("armor"));
                            }
                            catch (NumberFormatException e) {/* TODO ERROR */}
                        }
                        else {/* TODO ERROR */}

                        if(item.get("durability").getClass() == Long.class)
                        {
                            durability = Math.toIntExact((Long) item.get("durability"));
                        }
                        else if (item.get("durability").getClass() == String.class)
                        {
                            try
                            {
                                durability = Integer.parseInt((String) item.get("durability"));
                            }
                            catch (NumberFormatException e) {/* TODO ERROR */}
                        }
                        else {/* TODO ERROR */}

                        myItem = new MyItem(localizedName, itemType, baseItem, texture, maxStackSize, durability, color, armor, armorThougness);
                        break;
                    case ITEM:
                        myItem = new MyItem(localizedName, itemType, baseItem, texture, maxStackSize);
                        break;
                    case TOOL:


                        durability = 0;
                        int attackSpeed = 0;
                        int attackDamage = 0;

                        if(item.get("durability").getClass() == Long.class)
                        {
                            durability = Math.toIntExact((Long) item.get("durability"));
                        }
                        else if (item.get("durability").getClass() == String.class)
                        {
                            try
                            {
                                durability = Integer.parseInt((String) item.get("durability"));
                            }
                            catch (NumberFormatException e) {/* TODO ERROR */}
                        }
                        else {/* TODO ERROR */}

                        if(item.get("attackspeed").getClass() == Long.class)
                        {
                            attackSpeed = Math.toIntExact((Long) item.get("attackspeed"));
                        }
                        else if (item.get("attackspeed").getClass() == String.class)
                        {
                            try
                            {
                                attackSpeed = Integer.parseInt((String) item.get("attackspeed"));
                            }
                            catch (NumberFormatException e) {/* TODO ERROR */}
                        }
                        else {/* TODO ERROR */}

                        if(item.get("attackdamage").getClass() == Long.class)
                        {
                            attackDamage = Math.toIntExact((Long) item.get("attackdamage"));
                        }
                        else if (item.get("attackdamage").getClass() == String.class)
                        {
                            try
                            {
                                attackDamage = Integer.parseInt((String) item.get("attackdamage"));
                            }
                            catch (NumberFormatException e) {/* TODO ERROR */}
                        }
                        else {/* TODO ERROR */}

                        myItem = new MyItem(localizedName, itemType, baseItem, texture, maxStackSize, durability, attackSpeed , attackDamage);
                        break;
                    case BLOCK:
                        if(item.get("drops").getClass() != JSONArray.class) {/* TODO ERROR */}
                        JSONArray jsonArray = (JSONArray) item.get("drops");
                        ArrayList<String> drops = new ArrayList<>();

                        for(Object object : jsonArray)
                        {
                            if(object.getClass() != String.class)  {/* TODO ERROR */}

                            drops.add((String) object);
                        }
                        myItem = new MyItem(localizedName, itemType, baseItem, texture, maxStackSize, durability, drops);
                        break;
                }

                System.out.println(myItem.getType());


            }


        }
        catch (ParseException e) {/* TODO ERROR */}


        // TODO Logger print error in json

        // TODO Disable the plugin
    }


    public void onLoad()
    {
        JSONObject items = new JSONObject();

        try {
            items = (JSONObject) new JSONParser().parse(toParse);

            items.entrySet();


        } catch (ParseException e) {
            // TODO LOgger print error in json

            // TODO Disable the plugin
        }


    }

    public void onEnable()
    {

    }


    public void onDisable()
    {

    }
}
