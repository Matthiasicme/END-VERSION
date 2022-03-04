package com.company;

import java.util.ArrayList;
import java.util.List;

public class ClothingConfiguration {
    public List<Clothing> getAllClothes() {
        List<Clothing> results = new ArrayList<>();

        results.add(new Clothing(-50, 50, false, true, true, true, false, false, "Sunglasses", Clothing.Type.ACCESSORY));
        results.add(new Clothing(-50, 50, false, false, true, true, false, false, "UV cream", Clothing.Type.ACCESSORY));
        results.add(new Clothing(-50, 50, true, true, false, false, true, true, "Umbrella", Clothing.Type.ACCESSORY));
        results.add(new Clothing(10, 50, false, true, true, true, false, false, "Baseball cap", Clothing.Type.HEADGEAR));
        results.add(new Clothing(20, 50, false, false, false, true, false, false, "Summer hat", Clothing.Type.HEADGEAR));
        results.add(new Clothing(-50, 15, true, true, true, false, false, false, "Winter hat", Clothing.Type.HEADGEAR));
        results.add(new Clothing(0, 30, true, false, true, false, false, false, "Sneakers", Clothing.Type.SHOES));
        results.add(new Clothing(10, 35, false, false, true, false, false, false, "Flats", Clothing.Type.SHOES));
        results.add(new Clothing(20, 50, false, false, true, false, false, false, "Sandals", Clothing.Type.SHOES));
        results.add(new Clothing(-50, 25, true, true, true, false, false, false, "Boots", Clothing.Type.SHOES));
        results.add(new Clothing(-5, 30, true, true, true, false, false, false, "Fingerless gloves", Clothing.Type.HANDS));
        results.add(new Clothing(-10, 15, true, true, true, false, false, false, "Cotton gloves", Clothing.Type.HANDS));
        results.add(new Clothing(-50, 0, true, true, true, false, false, false, "Heavy gloves", Clothing.Type.HANDS));
        results.add(new Clothing(20, 50, true, false, true, false, false, false, "Shorts", Clothing.Type.LEGS));
        results.add(new Clothing(0, 25, true, false, true, false, false, false, "Long jeans", Clothing.Type.LEGS));
        results.add(new Clothing(10, 30, true, false, true, false, false, false, "Skinny jeans", Clothing.Type.LEGS));
        results.add(new Clothing(-5, 20, true, false, true, false, false, false, "Trousers", Clothing.Type.LEGS));
        results.add(new Clothing(-50, 0, true, false, true, false, false, false, "Warm trousers", Clothing.Type.LEGS));
        results.add(new Clothing(15, 50, false, false, true, false, false, false, "T-shirt", Clothing.Type.TORSO));
        results.add(new Clothing(10, 50, false, false, true, false, false, false, "Long shirt", Clothing.Type.TORSO));
        results.add(new Clothing(0, 25, false, false, true, false, false, false, "Light jacket", Clothing.Type.TORSO));
        results.add(new Clothing(-50, 10, true, true, true, false, false, false, "Jacket", Clothing.Type.TORSO));
        results.add(new Clothing(25, 50, false, false, true, false, false, false, "Bare torso", Clothing.Type.TORSO));
        results.add(new Clothing(0, 50, true, false, true, false, true, false, "Raincoat", Clothing.Type.TORSO));

        return results;
    }
}
