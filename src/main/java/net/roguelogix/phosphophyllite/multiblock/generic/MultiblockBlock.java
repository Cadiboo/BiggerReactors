package net.roguelogix.phosphophyllite.multiblock.generic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.roguelogix.phosphophyllite.registry.Registry;

public class MultiblockBlock extends Block {
    public MultiblockBlock(Properties properties) {
        super(properties);
    }
    
    protected MultiblockBakedModel model = null;
    
    public MultiblockBakedModel setupBakedModel(ResourceLocation defaultTexture) {
        model = new MultiblockBakedModel(defaultTexture);
        Registry.registerBakedModel(this, model);
        return model;
    }
    
    public boolean usesBlockState() {
        return true;
    }
    
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof MultiblockTile) {
            ActionResultType tileResult = ((MultiblockTile)te).onBlockActivated(state, worldIn, pos, player, handIn, hit);
            if(tileResult != ActionResultType.PASS){
                return tileResult;
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }
}
