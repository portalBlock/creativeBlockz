package net.portalblock.creativeblockz.worlds;

import net.portalblock.creativeblockz.CreativeBlockz;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by portalBlock on 2/22/14.
 */
public class LobbyGen extends ChunkGenerator {
    private CreativeBlockz instance;

    public LobbyGen(final CreativeBlockz instance){
        this.instance = instance;
    }

    public List<BlockPopulator> getDefaultPopulators(World world){
        return new ArrayList<BlockPopulator>();
    }

    @Override
    public byte[][] generateBlockSections(World world, Random random, int cX, int cZ, ChunkGenerator.BiomeGrid biomes){
        byte[][] result = new byte[256/16][];
        int x, y, z;

        for(x = 0; x < 16; x++){
            for(z = 0; z < 16; z++){
                setBlock(result, x, 0, z, (byte) Material.GRASS.getId());
            }
        }


        return result;
    }

    public short[][] generateExtSections(World w, Random r, int cX, int cY, int cZ, ChunkGenerator.BiomeGrid biomeGrid){
        short[][] result = new short[256/16][];
        int x, y, z;

        for(x = 0; x < 16; x++){
            for(z = 0; z < 16; z++){
                setBlock(result, x, 0, z, (short) Material.GRASS.getId());
            }
        }


        return result;
    }

    private void setBlock(byte[][] results, int x, int y, int z, byte blockId){
        if(results[y>>4] == null){
            results[y>>4] = new byte[4096];
        }
        results[y>>4][((y&0xF)) << 8 | z <<4 | x] = blockId;
    }

    private void setBlock(short[][] results, int x, int y, int z, short blockId){
        if(results[y>>4] == null){
            results[y>>4] = new short[4096];
        }
        results[y>>4][((y&0xF)) << 8 | z <<4 | x] = blockId;
    }

}
