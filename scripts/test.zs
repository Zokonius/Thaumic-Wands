import mods.thaumicwands.WandCaps;
import mods.thaumicwands.WandRods;

# Adds a new Wand Cap 
# String name, String research, float discount, ItemStack item, int craftCost
WandCaps.register("carrot","FIRSTSTEPS", 0.2, <minecraft:carrot>, 2);
game.setLocalization("item.wand.carrot.cap","Carrot Cultivated");

# Adds a new Wand Cap 
# String name, String research, float discount,AspectArray crystalDiscount, ItemStack item, int craftcost
WandCaps.register("diamond","FIRSTSTEPS", 2, [<aspect:aer>*2], <minecraft:diamond>,7);
game.setLocalization("item.wand.diamond.cap","Diamond Edged");

# Adds a new Wand Rod
# String name, String research, int capacity, ItemStack item, int craftCost
WandRods.register("beet", "FIRSTSTEPS", 10, <minecraft:beetroot>, 5);
game.setLocalization("item.wand.beet.rod","Beetroot");

# Removes a Wand Cap
# String name
WandCaps.remove("brass");

# Removes a Wand Cap
# ItemStack item
WandCaps.remove(<thaumicwands:item_wand_cap:1>);

# Removes a Wand Rod
# String name
WandRods.remove("silverwood");

# Removes a Wand Rod
# ItemStack item
WandRods.remove(<thaumicwands:item_wand_rod:0>);
