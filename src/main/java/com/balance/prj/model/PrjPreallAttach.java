package com.balance.prj.model;

/**
 * Created by dsy on 2017/9/10.
 * 现场图片 Dao
 */
public class PrjPreallAttach {
    private int id;
    private  String map_id;
    private String file_name;//文件名称
    private String file_path;  //文件路径
    private String created_at;  //创建时间
    private String created_by;  //创建人姓名
    private int type; //照片类型  1-拆除前  2-拆除中  3-拆除后

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMap_id() {
        return map_id;
    }

    public void setMap_id(String map_id) {
        this.map_id = map_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
