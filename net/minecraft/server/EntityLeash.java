package net.minecraft.server;

import java.util.Iterator;
import java.util.List;

public class EntityLeash extends EntityHanging {

    public EntityLeash(World world) {
        super(world);
    }

    public EntityLeash(World world, int i, int j, int k) {
        super(world, i, j, k, 0);
        this.setPosition((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D);
    }

    protected void c() {
        super.c();
    }

    public void setDirection(int i) {}

    public int f() {
        return 9;
    }

    public int i() {
        return 9;
    }

    public void b(Entity entity) {}

    public boolean d(NBTTagCompound nbttagcompound) {
        return false;
    }

    public void b(NBTTagCompound nbttagcompound) {}

    public void a(NBTTagCompound nbttagcompound) {}

    public boolean c(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.be();
        boolean flag = false;
        double d0;
        List list;
        Iterator iterator;
        EntityInsentient entityinsentient;

        if (itemstack != null && itemstack.getItem() == Items.LEASH && !this.world.isStatic) {
            d0 = 7.0D;
            list = this.world.a(EntityInsentient.class, AxisAlignedBB.a().a(this.locX - d0, this.locY - d0, this.locZ - d0, this.locX + d0, this.locY + d0, this.locZ + d0));
            if (list != null) {
                iterator = list.iterator();

                while (iterator.hasNext()) {
                    entityinsentient = (EntityInsentient) iterator.next();
                    if (entityinsentient.bL() && entityinsentient.getLeashHolder() == entityhuman) {
                        entityinsentient.setLeashHolder(this, true);
                        flag = true;
                    }
                }
            }
        }

        if (!this.world.isStatic && !flag) {
            this.die();
            if (entityhuman.abilities.canInstantlyBuild) {
                d0 = 7.0D;
                list = this.world.a(EntityInsentient.class, AxisAlignedBB.a().a(this.locX - d0, this.locY - d0, this.locZ - d0, this.locX + d0, this.locY + d0, this.locZ + d0));
                if (list != null) {
                    iterator = list.iterator();

                    while (iterator.hasNext()) {
                        entityinsentient = (EntityInsentient) iterator.next();
                        if (entityinsentient.bL() && entityinsentient.getLeashHolder() == this) {
                            entityinsentient.unleash(true, false);
                        }
                    }
                }
            }
        }

        return true;
    }

    public boolean survives() {
        return this.world.getType(this.x, this.y, this.z).b() == 11;
    }

    public static EntityLeash a(World world, int i, int j, int k) {
        EntityLeash entityleash = new EntityLeash(world, i, j, k);

        entityleash.o = true;
        world.addEntity(entityleash);
        return entityleash;
    }

    public static EntityLeash b(World world, int i, int j, int k) {
        List list = world.a(EntityLeash.class, AxisAlignedBB.a().a((double) i - 1.0D, (double) j - 1.0D, (double) k - 1.0D, (double) i + 1.0D, (double) j + 1.0D, (double) k + 1.0D));

        if (list != null) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                EntityLeash entityleash = (EntityLeash) iterator.next();

                if (entityleash.x == i && entityleash.y == j && entityleash.z == k) {
                    return entityleash;
                }
            }
        }

        return null;
    }
}
