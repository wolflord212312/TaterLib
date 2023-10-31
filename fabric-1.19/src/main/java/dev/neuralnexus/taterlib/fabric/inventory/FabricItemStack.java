package dev.neuralnexus.taterlib.fabric.inventory;

import dev.neuralnexus.taterlib.common.inventory.AbstractItemMeta;
import dev.neuralnexus.taterlib.common.inventory.AbstractItemStack;
import net.minecraft.item.ItemStack;

/**
 * Abstracts a Fabric item stack to an AbstractItemStack.
 */
public class FabricItemStack implements AbstractItemStack {
    private final ItemStack itemStack;

    /**
     * Constructor.
     * @param itemStack The Fabric item stack.
     */
    public FabricItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Getter for the Fabric item stack.
     * @return The Fabric item stack.
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemMeta getMeta() {
        return new FabricItemMeta(itemStack);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setMeta(AbstractItemMeta itemMeta) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getType() {
        String itemName = itemStack.getItem().toString();
        if (!itemName.contains(":")) {
            return "minecraft:" + itemName;
        }
        return itemName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getCount() {
        return itemStack.getCount();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCount(int count) {
        itemStack.setCount(count);
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack clone() {
        return new FabricItemStack(itemStack.copy());
    }
}
