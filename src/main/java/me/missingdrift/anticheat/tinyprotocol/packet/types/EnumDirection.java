package me.missingdrift.anticheat.tinyprotocol.packet.types;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum EnumDirection {
    DOWN(1, -1, "down", EnumAxisDirection.NEGATIVE, EnumAxis.Y, new BaseBlockPosition(0, -1, 0)),
    UP(0, -1, "up", EnumAxisDirection.POSITIVE, EnumAxis.Y, new BaseBlockPosition(0, 1, 0)),
    NORTH(3, 2, "north", EnumAxisDirection.NEGATIVE, EnumAxis.Z, new BaseBlockPosition(0, 0, -1)),
    SOUTH(2, 0, "south", EnumAxisDirection.POSITIVE, EnumAxis.Z, new BaseBlockPosition(0, 0, 1)),
    WEST(5, 1, "west", EnumAxisDirection.NEGATIVE, EnumAxis.X, new BaseBlockPosition(-1, 0, 0)),
    EAST(4, 3, "east", EnumAxisDirection.POSITIVE, EnumAxis.X, new BaseBlockPosition(1, 0, 0));

    private static final EnumDirection[] n = new EnumDirection[6];
    private static final EnumDirection[] o = new EnumDirection[4];
    private static final Map<String, EnumDirection> p = new HashMap<>();

    static {
        EnumDirection[] var0 = values();

        for (EnumDirection var3 : var0) {
            n[var3.ordinal()] = var3;
            if (var3.k().c()) {
                o[var3.i] = var3;
            }

            p.put(var3.j().toLowerCase(), var3);
        }

    }

    private final int h;
    private final int i;
    private final String j;
    private final EnumAxis k;
    private final EnumAxisDirection l;
    private final BaseBlockPosition m;

    EnumDirection(int order, int offset, String direction, EnumAxisDirection axisDirection, EnumAxis axis, BaseBlockPosition offsetPosition) {
        this.i = offset;
        this.h = order;
        this.j = direction;
        this.k = axis;
        this.l = axisDirection;
        this.m = offsetPosition;
    }

    public static EnumDirection fromType1(int var0) {
        return n[MathHelper.a(var0 % n.length)];
    }

    public static EnumDirection fromType2(int var0) {
        return o[MathHelper.a(var0 % o.length)];
    }

    public static EnumDirection fromAngle(double var0) {
        return fromType2(MathHelper.floor(var0 / 90.0D + 0.5D) & 3);
    }

    public static EnumDirection a(Random var0) {
        return values()[var0.nextInt(values().length)];
    }

    public static EnumDirection a(EnumAxisDirection var0, EnumAxis var1) {
        EnumDirection[] var2 = values();
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            EnumDirection var5 = var2[var4];
            if (var5.c() == var0 && var5.k() == var1) {
                return var5;
            }
        }

        throw new IllegalArgumentException("No such direction: " + var0 + " " + var1);
    }

    public int b() {
        return this.i;
    }

    public EnumAxisDirection c() {
        return this.l;
    }

    public EnumDirection opposite() {
        return fromType1(this.h);
    }

    public int getAdjacentX() {
        return this.k == EnumAxis.X ? this.l.a() : 0;
    }

    public int getAdjacentY() {
        return this.k == EnumAxis.Y ? this.l.a() : 0;
    }

    public int getAdjacentZ() {
        return this.k == EnumAxis.Z ? this.l.a() : 0;
    }

    public String j() {
        return this.j;
    }

    public EnumAxis k() {
        return this.k;
    }

    public String toString() {
        return this.j;
    }

    public String getName() {
        return this.j;
    }

    public enum EnumDirectionLimit {
        HORIZONTAL,
        VERTICAL;

        public boolean a(EnumDirection var1) {
            return var1 != null && var1.k().d() == this;
        }
    }

    public enum EnumAxisDirection {
        POSITIVE(1, "Towards positive"),
        NEGATIVE(-1, "Towards negative");

        private final int c;
        private final String d;

        EnumAxisDirection(int var3, String var4) {
            this.c = var3;
            this.d = var4;
        }

        public int a() {
            return this.c;
        }

        public String toString() {
            return this.d;
        }
    }

    public enum EnumAxis {
        X("x", EnumDirectionLimit.HORIZONTAL),
        Y("y", EnumDirectionLimit.VERTICAL),
        Z("z", EnumDirectionLimit.HORIZONTAL);

        private static final Map<String, EnumAxis> d = new HashMap<>();

        static {
            EnumAxis[] var0 = values();
            int var1 = var0.length;

            for (int var2 = 0; var2 < var1; ++var2) {
                EnumAxis var3 = var0[var2];
                d.put(var3.a().toLowerCase(), var3);
            }
        }

        private final String e;
        private final EnumDirectionLimit f;

        EnumAxis(String var3, EnumDirectionLimit var4) {
            this.e = var3;
            this.f = var4;
        }

        public String a() {
            return this.e;
        }

        public boolean b() {
            return this.f == EnumDirectionLimit.VERTICAL;
        }

        public boolean c() {
            return this.f == EnumDirectionLimit.HORIZONTAL;
        }

        public String toString() {
            return this.e;
        }

        public boolean a(EnumDirection var1) {
            return var1 != null && var1.k() == this;
        }

        public EnumDirectionLimit d() {
            return this.f;
        }

        public String getName() {
            return this.e;
        }
    }
}
