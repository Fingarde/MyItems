package fr.fingarde.myitems;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class Main extends JavaPlugin
{
    public void onLoad()
    {
        getDataFolder().mkdirs();
        loadObjects();
    }

    public void onEnable()
    {

    }


    public void onDisable()
    {

    }


    public void loadObjects()
    {
        File file = new File(getDataFolder(),"items.json");
        try
        {
            String json = "";
            for(String line : Files.readAllLines(file.toPath())) json += line;


            JSONObject items;

            items = (JSONObject) new JSONParser().parse(json);

            for(Object localizedItem : items.keySet())
            {
                if(localizedItem.getClass() != String.class) {printError(localizedItem.toString(), "localizedName is not a string");}

                String localizedName = (String) localizedItem;

                JSONObject item = (JSONObject) items.get((localizedItem));

                if(!item.containsKey("name"))  printError(localizedItem.toString(), "The item don't have name");
                if(item.get("name").getClass() != String.class) {printError(localizedItem.toString(), "name is not a string");}

                if(!item.containsKey("texture"))  printError(localizedItem.toString(), "The item don't have texture name");
                if(item.get("texture").getClass() != String.class) {printError(localizedItem.toString(), "textureName is not a string");}

                String name = (String)item.get("name");
                String texture = (String)item.get("texture");

                int maxStackSize = 0;

                if(!item.containsKey("stack"))  printError(localizedItem.toString(), "The item don't have max stack size");
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
                    catch (NumberFormatException e) {printError(localizedItem.toString(), "maxStackSize isn't a valid number");}
                }
                else {printError(localizedItem.toString(), "maxStackSize isn't a valid number");}

                if(maxStackSize > 64 ||maxStackSize < 1) printError(localizedItem.toString(), "maxStackSize isn't a valid number");

                if(!item.containsKey("type"))  printError(localizedItem.toString(), "The item don't have type");
                if(item.get("type").getClass() != String.class) {printError(localizedItem.toString(), "type is not a string");}

                MyItem.ItemType itemType = null;
                try
                {
                    itemType = MyItem.ItemType.valueOf(((String) item.get("type")).toUpperCase());

                }
                catch (IllegalArgumentException e) {printError(localizedItem.toString(), "itemType isn't correct");}

                if(!item.containsKey("baseitem"))  printError(localizedItem.toString(), "The item don't have base item");
                if(item.get("baseitem").getClass() != String.class) {printError(localizedItem.toString(), "baseitem is not a string");}

                MyItem.BaseItem baseItem = null;
                try
                {
                    baseItem = MyItem.BaseItem.valueOf(((String) item.get("baseitem")).toUpperCase());

                }
                catch (IllegalArgumentException e) {printError(localizedItem.toString(), "baseItem isn't correct");}

                int durability = 0;

                MyItem myItem = null;
                switch (itemType)
                {
                    case ARMOR:
                        if(!item.containsKey("color"))  printError(localizedItem.toString(), "The item don't have color");
                        if(item.get("color").getClass() != String.class) {printError(localizedItem.toString(), "color is not a string");}

                        String colorStr = (String) item.get("color");

                        if(colorStr.startsWith("#")) colorStr = colorStr.substring(2);
                        if(colorStr.length() < 6) {printError(localizedItem.toString(), "color is not in a good format");}

                        durability = 0;
                        Color color = null;
                        int armor = 0;
                        int armorThougness = 0;

                        if(!item.containsKey("armor"))  printError(localizedItem.toString(), "The item don't have armor value");
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
                            catch (NumberFormatException e) {printError(localizedItem.toString(), "maxStackSize isn't a valid number");}
                        }
                        else {printError(localizedItem.toString(), "maxStackSize isn't a valid number");}


                        if(!item.containsKey("armorthougness")) printError(localizedItem.toString(), "The item don't have armorthougness value");
                        if(item.get("armorthougness").getClass() == Long.class)
                        {
                            armorThougness = Math.toIntExact((Long) item.get("armorthougness"));
                        }
                        else if (item.get("armorthougness").getClass() == String.class)
                        {
                            try
                            {
                                armorThougness = Integer.parseInt((String) item.get("armorthougness"));
                            }
                            catch (NumberFormatException e) {printError(localizedItem.toString(), "armorThougness isn't a valid number");}
                        }
                        else {printError(localizedItem.toString(), "armorThougness isn't a valid number");}

                        if(!item.containsKey("durability")) printError(localizedItem.toString(), "The item don't have durability value");

                        if (item.get("durability").getClass() == Long.class)
                        {
                            durability = Math.toIntExact((Long) item.get("durability"));
                        } else if (item.get("durability").getClass() == String.class)
                        {
                            try
                            {
                                durability = Integer.parseInt((String) item.get("durability"));
                            } catch (NumberFormatException e)
                            {
                                printError(localizedItem.toString(), "durability isn't a valid number");
                            }
                        } else
                        {
                            printError(localizedItem.toString(), "durability isn't a valid number");
                        }

                        if(durability < 0) printError(localizedItem.toString(), "durability can't be lower than 0, 0 is unbreakable");

                        myItem = new MyItem(localizedName, name, itemType, baseItem, texture, maxStackSize, durability, color, armor, armorThougness);
                        break;
                    case ITEM:
                        myItem = new MyItem(localizedName, name, itemType, baseItem, texture, maxStackSize);
                        break;
                    case TOOL:
                        durability = 0;
                        int attackSpeed = 0;
                        int attackDamage = 0;

                        if(!item.containsKey("durability"))  printError(localizedItem.toString(), "The item don't have durability value");

                        if (item.get("durability").getClass() == Long.class)
                        {
                            durability = Math.toIntExact((Long) item.get("durability"));
                        } else if (item.get("durability").getClass() == String.class)
                        {
                            try
                            {
                                durability = Integer.parseInt((String) item.get("durability"));
                            } catch (NumberFormatException e)
                            {
                                printError(localizedItem.toString(), "durability isn't a valid number");
                            }
                        } else
                        {
                            printError(localizedItem.toString(), "durability isn't a valid number");
                        }

                        if(durability < 0) printError(localizedItem.toString(), "durability can't be lower than 0, 0 is unbreakable");

                        if(!item.containsKey("attackspeed")) printError(localizedItem.toString(), "The item don't have attackspeed value");
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
                            catch (NumberFormatException e) {printError(localizedItem.toString(), "attackSpeed isn't a valid number");}
                        }
                        else {printError(localizedItem.toString(), "attackSpeed isn't a valid number");}

                        if(!item.containsKey("attackdamage")) printError(localizedItem.toString(), "The item don't have attackdamage value");
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
                            catch (NumberFormatException e) {printError(localizedItem.toString(), "attackDamage isn't a valid number");}
                        }
                        else {printError(localizedItem.toString(), "attackDamage isn't a valid number");}


                        myItem = new MyItem(localizedName, name, itemType, baseItem, texture, maxStackSize, durability, attackSpeed , attackDamage);
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
                        myItem = new MyItem(localizedName, name, itemType, baseItem, texture, maxStackSize, durability, drops);
                        break;
                }

                System.out.println(myItem.getDurability());
            }
        }
        catch (ParseException e) {printError("JSON", "Error in the JSON");}
        catch (IOException e) {printError("File", "Can't read file: items.json");}
    }

    public void printError(String localizedName, String error)
    {
        Logger.printError("Error while loading item " + ChatColor.RED + localizedName + ChatColor.RESET + " with error: " + ChatColor.YELLOW + error);
    }
}
