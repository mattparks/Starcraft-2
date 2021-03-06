package mattparks.mods.starcraft.mercury.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * This file is part of the 4-Space project
 * 
 * @author mattparks
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */

public class SCMercuryStone extends Block
{
    public SCMercuryStone(int par1, String name)
    {
        super(par1, Material.rock);
        this.setUnlocalizedName(name);
//        this.setTextureName(StarcraftCore.ASSET_PREFIX + name);
        this.setHardness(2.0F);
    }
    
    @Override
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return MercuryBlocks.MercuryCobblestone.blockID;
    }
}
