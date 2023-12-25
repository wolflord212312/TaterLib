package dev.neuralnexus.taterlib.forge.inventory;

import dev.neuralnexus.taterlib.inventory.ItemStack;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;

import net.minecraft.entity.player.InventoryPlayer;

/** Abstracts a Forge player inventory to an AbstractPlayerInventory. */
public class ForgePlayerInventory extends ForgeInventory implements PlayerInventory {
    private final InventoryPlayer playerInventory;

    /**
     * Constructor.
     *
     * @param playerInventory The Forge player inventory.
     */
    public ForgePlayerInventory(InventoryPlayer playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack[] getArmorContents() {
        ItemStack[] armorContents = new ItemStack[4];
        for (int i = 0; i < 4; i++) {
            armorContents[i] = new ForgeItemStack(playerInventory.armorInventory[i]);
        }
        return armorContents;
    }

    /** {@inheritDoc} */
    @Override
    public void setArmorContents(ItemStack[] items) {
        clear();
        for (int i = 0; i < 4; i++) {
            playerInventory.armorInventory[i] = ((ForgeItemStack) items[i]).getItemStack();
        }
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack[] getExtraContents() {
        // 1.8.9 doesn't have offhand
        return new ItemStack[2];
    }

    /** {@inheritDoc} */
    @Override
    public void setExtraContents(ItemStack[] items) {
        // TODO: Implement
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getHelmet() {
        return new ForgeItemStack(playerInventory.armorInventory[0]);
    }

    /** {@inheritDoc} */
    @Override
    public void setHelmet(ItemStack item) {
        playerInventory.armorInventory[0] = ((ForgeItemStack) item).getItemStack();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getChestplate() {
        return new ForgeItemStack(playerInventory.armorInventory[1]);
    }

    /** {@inheritDoc} */
    @Override
    public void setChestplate(ItemStack item) {
        playerInventory.armorInventory[1] = ((ForgeItemStack) item).getItemStack();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getLeggings() {
        return new ForgeItemStack(playerInventory.armorInventory[2]);
    }

    /** {@inheritDoc} */
    @Override
    public void setLeggings(ItemStack item) {
        playerInventory.armorInventory[2] = ((ForgeItemStack) item).getItemStack();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getBoots() {
        return new ForgeItemStack(playerInventory.armorInventory[3]);
    }

    /** {@inheritDoc} */
    @Override
    public void setBoots(ItemStack item) {
        playerInventory.armorInventory[3] = ((ForgeItemStack) item).getItemStack();
    }

    /** {@inheritDoc} */
    @Override
    public void setItem(String type, ItemStack item) {
        // TODO: Implement
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getItem(String type) {
        // TODO: Implement
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getItemInMainHand() {
        // TODO: Implement
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void setItemInMainHand(ItemStack item) {
        // TODO: Implement
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getItemInOffHand() {
        // TODO: Implement
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void setItemInOffHand(ItemStack item) {
        // TODO: Implement
    }

    /** {@inheritDoc} */
    @Override
    public int getHeldItemSlot() {
        return playerInventory.currentItem;
    }
}