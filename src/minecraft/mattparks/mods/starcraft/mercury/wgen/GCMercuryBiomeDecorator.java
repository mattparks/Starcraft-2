package mattparks.mods.starcraft.mercury.wgen;

import java.util.Random;

import mattparks.mods.starcraft.spacecraftBlocks.SpacecraftBlocks;
import micdoodle8.mods.galacticraft.api.event.wgen.GCCoreEventPopulate;
import micdoodle8.mods.galacticraft.core.world.gen.GCCoreWorldGenMinableMeta;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;

public class GCMercuryBiomeDecorator
{
    protected World worldObj;
    protected Random randomGenerator;

    protected int chunkX;
    protected int chunkZ;

    protected WorldGenerator dirtGen;
    protected WorldGenerator gemGen;
    protected WorldGenerator coalGen;
    protected WorldGenerator tinGen;
    protected WorldGenerator copperGen;

    public GCMercuryBiomeDecorator(BiomeGenBase par1BiomeGenBase)
    {
        this.gemGen = new GCCoreWorldGenMinableMeta(SpacecraftBlocks.MercuryIridiumOre.blockID, 4, 0, true, SpacecraftBlocks.MercuryIridiumOre.blockID, 4);
        this.dirtGen = new GCCoreWorldGenMinableMeta(SpacecraftBlocks.MercuryDirt.blockID, 32, 3, true, SpacecraftBlocks.MercuryDirt.blockID, 4); 
        this.coalGen = new GCCoreWorldGenMinableMeta(SpacecraftBlocks.MercuryCoalOre.blockID, 32, 3, true, SpacecraftBlocks.MercuryCoalOre.blockID, 4);     
        this.tinGen = new GCCoreWorldGenMinableMeta(SpacecraftBlocks.MercuryTinOre.blockID, 32, 3, true, SpacecraftBlocks.MercuryTinOre.blockID, 4);  
        this.copperGen = new GCCoreWorldGenMinableMeta(SpacecraftBlocks.MercuryCopperOre.blockID, 32, 3, true,SpacecraftBlocks.MercuryCopperOre.blockID, 4);      
    }

    public void decorate(World worldObj, Random rand, int chunkX, int chunkZ)
    {
        if (this.worldObj != null)
        {
            throw new RuntimeException("Already decorating!!");
        }
        else
        {
            this.worldObj = worldObj;
            this.randomGenerator = rand;
            this.chunkX = chunkX;
            this.chunkZ = chunkZ;
            this.generateVenus();
            this.worldObj = null;
            this.randomGenerator = null;
        }
    }

    protected void genStandardOre1(int amountPerChunk, WorldGenerator worldGenerator, int minY, int maxY)
    {
        for (int var5 = 0; var5 < amountPerChunk; ++var5)
        {
            final int var6 = this.chunkX + this.randomGenerator.nextInt(16);
            final int var7 = this.randomGenerator.nextInt(maxY - minY) + minY;
            final int var8 = this.chunkZ + this.randomGenerator.nextInt(16);
            worldGenerator.generate(this.worldObj, this.randomGenerator, var6, var7, var8);
        }
    }

    protected void generateVenus()
    {
        MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Pre(this.worldObj, this.randomGenerator, this.chunkX, this.chunkZ));
        this.genStandardOre1(20, this.dirtGen, 0, 200);
        this.genStandardOre1(26, this.gemGen, 0, 60);
        this.genStandardOre1(36, this.coalGen, 0, 128);
        this.genStandardOre1(35, this.tinGen, 0, 128);
        this.genStandardOre1(36, this.copperGen, 0, 128);
        MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Post(this.worldObj, this.randomGenerator, this.chunkX, this.chunkZ));
    }
}