package net.ap.lotm.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.UnknownNullability;

public class LotmData implements ILotmData {

    private final LivingEntity entity;
    private boolean initialized;
    private int age;

    public LotmData(LivingEntity entity) {
        this.entity = entity;
    }

    @Override
    public void tick() {

        if(!this.initialized) {
            this.initialized = true;
            gen();
        }

    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void gen() {
        this.initialized = true;
        this.age = (int) (Math.random() * 100);
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();

        tag.putBoolean("init", this.initialized);
        tag.putInt("age", this.age);

        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {

        this.initialized = tag.getBoolean("init");
        this.age = tag.getInt("age");

    }



}
