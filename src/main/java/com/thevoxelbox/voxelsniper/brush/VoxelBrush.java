package com.thevoxelbox.voxelsniper.brush;

import com.thevoxelbox.voxelsniper.Message;
import com.thevoxelbox.voxelsniper.SnipeData;
import com.thevoxelbox.voxelsniper.brush.perform.PerformBrush;

/**
 * http://www.voxelwiki.com/minecraft/Voxelsniper#The_Voxel_Brush
 *
 * @author Piotr
 */
public class VoxelBrush extends PerformBrush
{
    private static int timesUsed = 0;

    /**
     *
     */
    public VoxelBrush()
    {
        this.setName("Voxel");
    }

    private void voxel(final SnipeData v)
    {
        for (int z = v.getBrushSize(); z >= -v.getBrushSize(); z--)
        {
            for (int x = v.getBrushSize(); x >= -v.getBrushSize(); x--)
            {
                for (int y = v.getBrushSize(); y >= -v.getBrushSize(); y--)
                {
                    this.current.perform(this.clampY(this.getTargetBlock().getX() + x, this.getTargetBlock().getY() + z, this.getTargetBlock().getZ() + y));
                }
            }
        }
        v.storeUndo(this.current.getUndo());
    }

    @Override
    protected final void arrow(final SnipeData v)
    {
        this.voxel(v);
    }

    @Override
    protected final void powder(final SnipeData v)
    {
        this.voxel(v);
    }

    @Override
    public final void info(final Message vm)
    {
        vm.brushName(this.getName());
        vm.size();
    }

    @Override
    public final int getTimesUsed()
    {
        return VoxelBrush.timesUsed;
    }

    @Override
    public final void setTimesUsed(final int tUsed)
    {
        VoxelBrush.timesUsed = tUsed;
    }
}
