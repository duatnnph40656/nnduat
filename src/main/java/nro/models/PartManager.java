/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nro.models;

import com.google.gson.Gson;
import nro.jdbc.DBService;
import nro.server.io.Message;
import nro.utils.Log;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 *
 * @author kitakeyos - Hoàng Hữu Dũng
 */
public class PartManager {

    private static final PartManager instance = new PartManager();

    public static PartManager getInstance() {
        return instance;
    }

    @Getter
    private List<Part> parts;
    @Getter
    private byte[] data;

    public PartManager() {
        parts = new ArrayList<>();
    }

    public void load() {
        try {
            Gson g = new Gson();
            PreparedStatement ps = DBService.gI().getConnectionForGame().prepareStatement("SELECT * FROM part");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                short id = rs.getShort("id");
                byte type = rs.getByte("type");
                PartImage[] pi = g.fromJson(rs.getString("part"), PartImage[].class);
                Part part = new Part();
                part.setId(id);
                part.setType(type);
                part.setPi(pi);
                add(part);
            }
            Log.success("Load part thành công (" + parts.size() + ")");
            rs.close();
            ps.close();
            setData();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void add(Part part) {
        parts.add(part);
    }

    public void remove(Part part) {
        parts.remove(part);
    }

    public Part find(int id) {
        for (Part part : parts) {
            if (part.getId() == id) {
                return part;
            }
        }
        return null;
    }

    public Part get(int index) {
        return parts.get(index);
    }

    public void setData() {
        try {
            Message ms = new Message();
            DataOutputStream ds = ms.writer();
            ds.writeShort(parts.size());
            for (Part part : parts) {
                ds.writeByte(part.getType());
                PartImage[] partImages = part.getPi();
                for (PartImage partDetail : partImages) {
                    ds.writeShort(partDetail.getIcon());
                    ds.writeByte(partDetail.getDx());
                    ds.writeByte(partDetail.getDy());
                }
            }
            ds.flush();
            this.data = ms.getData();
            ds.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
