package com.heyimamethyst.fairyfactions.common.entities.ai;

import com.heyimamethyst.fairyfactions.common.entities.FairyEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.NodeEvaluator;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

@Deprecated
public class FairyMoveControl extends MoveControl
{

    public FairyMoveControl(Mob pMob)
    {
        super(pMob);
    }

    @Override
    public void tick()
    {
        if (this.operation == MoveControl.Operation.MOVE_TO)
        {
            this.operation = MoveControl.Operation.WAIT;

            double d0 = this.wantedX - this.mob.getX();
            double d1 = this.wantedZ - this.mob.getZ();
            double d2 = this.wantedY - this.mob.getY();

            double d3 = d0 * d0 + d2 * d2 + d1 * d1;

            if (d3 < (double)2.5000003E-7F)
            {
                this.mob.setZza(0.0F);
                return;
            }

            if(mob instanceof FairyEntity)
            {
                FairyEntity fairy = (FairyEntity)mob;

                if(!fairy.flymode())
                {
                    float f9 = (float)(Mth.atan2(d1, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f9, 90.0F));

                    float f1 = (float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    this.mob.setSpeed(f1);

                    BlockPos blockpos = this.mob.blockPosition();
                    BlockState blockstate = this.mob.level.getBlockState(blockpos);
                    VoxelShape voxelshape = blockstate.getCollisionShape(this.mob.level, blockpos);

                    if (d2 > (double)this.mob.getStepHeight() && d0 * d0 + d1 * d1 < (double)Math.max(1.0F, this.mob.getBbWidth()) || !voxelshape.isEmpty() && this.mob.getY() < voxelshape.max(Direction.Axis.Y) + (double)blockpos.getY() && !blockstate.is(BlockTags.DOORS) && !blockstate.is(BlockTags.FENCES))
                    {
                        this.mob.getJumpControl().jump();
                        this.operation = MoveControl.Operation.JUMPING;
                    }
                }
                else
                {

//                    float f9 = (float)(Mth.atan2(d1, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
//                    this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f9, 90.0F));

//                    if(this.mob.position().closerThan(new Vec3(this.wantedX, this.wantedY, this.wantedZ), 1))
//                    {
//                        this.mob.getNavigation().stop();
//                    }
//                    else
//                    {
//                        this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f9, 90.0F));
//                    }

                    Vec3 vec3 = new Vec3(d0, d2, d1);
                    vec3 = vec3.normalize();

                    if(vec3 != Vec3.ZERO)
                    {
                        float f9 = (float)(Mth.atan2(d1, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                        this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f9, 90.0F));
                    }

                    float f1 = (float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    this.mob.setSpeed(f1);

                    BlockPos blockpos = this.mob.blockPosition();
                    BlockState blockstate = this.mob.level.getBlockState(blockpos);
                    VoxelShape voxelshape = blockstate.getCollisionShape(this.mob.level, blockpos);

                    if (d2 > (double)this.mob.getStepHeight() && d0 * d0 + d1 * d1 < (double)Math.max(1.0F, this.mob.getBbWidth()) || !voxelshape.isEmpty() && this.mob.getY() < voxelshape.max(Direction.Axis.Y) + (double)blockpos.getY() && !blockstate.is(BlockTags.DOORS) && !blockstate.is(BlockTags.FENCES))
                    {
                        this.mob.getJumpControl().jump();
                        this.operation = MoveControl.Operation.JUMPING;
                    }

//                    double d4 = Math.sqrt(d0 * d0 + d1 * d1);
//
//                    if (Math.abs(d2) > (double)1.0E-5F || Math.abs(d4) > (double)1.0E-5F)
//                    {
//                        float f2 = (float)(-(Mth.atan2(d2, d4) * (double)(180F / (float)Math.PI)));
//                        //this.mob.setXRot(this.rotlerp(this.mob.getXRot(), f2, (float)this.maxTurn));
//                        this.mob.setYya(d2 > 0.0D ? f1 : -f1);
//                    }
                }

//                float f9 = (float)(Mth.atan2(d1, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
//                this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f9, 90.0F));
//
//                float f1 = (float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
//                this.mob.setSpeed(f1);
//
//                BlockPos blockpos = this.mob.blockPosition();
//                BlockState blockstate = this.mob.level.getBlockState(blockpos);
//                VoxelShape voxelshape = blockstate.getCollisionShape(this.mob.level, blockpos);
//
//                if (d2 > (double)this.mob.getStepHeight() && d0 * d0 + d1 * d1 < (double)Math.max(1.0F, this.mob.getBbWidth()) || !voxelshape.isEmpty() && this.mob.getY() < voxelshape.max(Direction.Axis.Y) + (double)blockpos.getY() && !blockstate.is(BlockTags.DOORS) && !blockstate.is(BlockTags.FENCES))
//                {
//                    this.mob.getJumpControl().jump();
//                    this.operation = MoveControl.Operation.JUMPING;
//                }
            }

//            double d3 = d0 * d0 + d2 * d2 + d1 * d1;
//
//            if (d3 < (double)2.5000003E-7F)
//            {
//                this.mob.setZza(0.0F);
//                return;
//            }
//
//            float f9 = (float)(Mth.atan2(d1, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
//            this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f9, 90.0F));
//
//            float f1 = (float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
//            this.mob.setSpeed(f1);
//
//            BlockPos blockpos = this.mob.blockPosition();
//            BlockState blockstate = this.mob.level.getBlockState(blockpos);
//            VoxelShape voxelshape = blockstate.getCollisionShape(this.mob.level, blockpos);
//
//            if (d2 > (double)this.mob.getStepHeight() && d0 * d0 + d1 * d1 < (double)Math.max(1.0F, this.mob.getBbWidth()) || !voxelshape.isEmpty() && this.mob.getY() < voxelshape.max(Direction.Axis.Y) + (double)blockpos.getY() && !blockstate.is(BlockTags.DOORS) && !blockstate.is(BlockTags.FENCES))
//            {
//                this.mob.getJumpControl().jump();
//                this.operation = MoveControl.Operation.JUMPING;
//            }
        }
        else if (this.operation == MoveControl.Operation.JUMPING)
        {
            this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));

            if (this.mob.isOnGround())
            {
                this.operation = MoveControl.Operation.WAIT;
            }
        }
        else
        {

            this.mob.setYya(0.0F); //
            this.mob.setZza(0.0F);
        }

    }
}
