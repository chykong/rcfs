<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<table id="basic-table" class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th>编号</th>
        <th>被搬迁人</th>
        <th>房屋坐落</th>
        <th>门牌号</th>
        <th>身份证号</th>
        <th>联系电话</th>
        <th>联系电话2</th>
        <th>实测宅基地面积(m²)</th>
        <th>认定宅基地面积(m²)</th>
        <th>实测房屋面积(m²)</th>
        <th>认定房屋面积(m²)</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tfoot id="table-foot">
    <tr>
        <th>当前页合计：</th>
        <th></th>
        <th></th>
        <th></th>
        <th></th>
        <th></th>
        <th></th>
        <th class="isSum"></th>
        <th class="isSum"></th>
        <th class="isSum"></th>
        <th class="isSum"></th>
        <th></th>
        <th></th>
    </tr>
    </tfoot>
</table>

<script>
    $(function () {
        var $table_id = $("#basic-table");
        var table = $table_id.DataTable({
            dom: '<"row"<"col-sm-12"tr>><"row"<"col-sm-5"i><"col-sm-7"p>>',
            filter: false, //列筛序功能
            searching: false,//本地搜索
            ordering: false, //排序功能
            info: true,//页脚信息
            autoWidth: true,//自动宽度
            scrollX: true,
            scrollY: true,
            paginate: true, //翻页功能
            sortable: false,
            lengthChange: true,
            lengthMenu: [15, 25, 50, 75, 100],
            processing: true,
            serverSide: true,
            ajax: function (data, callback, settings) {//ajax配置为function,手动调用异步查询
                $.ajax({
                    async: false,
                    type: "GET",
                    url: '<c:url value="/prj/preallocation/basic/getPreallocation.htm"/>',
                    data: {
                        mapId: $("#mapId").val(),
                        hostName: $("#hostName").val(),
                        location: $("#location").val(),
                        section: $("#section").val(),
                        groups: $("#groups").val(),
                        page: parseInt(data.start/data.length) + 1,
                        size: data.length
                    },
                    cache: false,  //禁用缓存
                    dataType: "json",
                    success: function (result) {
                        result.draw = result.searchDTO.draw;
                        callback(result);
                    }
                })
            },
            //使用对象数组，一定要配置columns，告诉 DataTables 每列对应的属性
            //data 这里是固定不变的，id，name，age，sex等为你数据里对应的属性
            columns: [
                {
                    data: "mapId",
                    width: "200px",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "hostName",
                    width: "80px",
                    render: function (data, type, row) {
                        return '<a href="<c:url value="/prj/preallocation/basic/view.htm"/>?id=' +
                            row.id + '">'+ data+ '</a>';
                    }
                },
                {
                    data: "location",
                    width: "300px",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "mp",
                    width: "80px",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "idCard",
                    width: "150px",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "hostPhone",
                    width: "90px",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "hostPhoneBack",
                    width: "90px",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "actualHomesteadArea",
                    width: "100px",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "cogHomesteadArea",
                    width: "100px",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "actualHouseArea",
                    width: "100px",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "cogHouseArea",
                    width: "100px",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "status",
                    width: "120px",
                    render: function (data) {
                        switch (data) {
                            case 10:
                                return '<span class="label label-default ">未签约</span>';
                            case 20:
                                return '<span class="label label-warning ">已签约</span>';
                            case 30:
                                return '<span class="label label-warning ">已审核</span>';
                            case 40:
                                return '<span class="label label-success ">已交房</span>';
                            case 50:
                                return '<span class="label label-success ">已拆除</span>';
                            case 60:
                                return '<span class="label label-success ">已放款</span>';
                            case 70:
                                return '<span class="label label-success ">已归档</span>';
                            default:
                                return '<span class="label label-error ">已归档</span>';
                        }
                    }
                },
                {
                    data: "id",
                    width: "80px",
                    render: function (data,type,row) {
                        if (row.status == 10 || row.status == 20) {
                            return '<a class="btn-sm btn-info" href="/prj/preallocation/basic/toUpdate.htm?backUrl=${backUrl}&id="/>' + data + '">\
                                    <i class="ace-icon fa fa-pencil-square-o "></i>修改</a>';
                        } else {
                            return '';
                        }
                    }
                }
            ],
            fixedColumns: {
                leftColumns: 2,
                rightColumns:0
            },
            language: {
                url: '<c:url value="/assets/datatables/i18n/Chinese.json"/>'
            },
            footerCallback: function () {
                var api = this.api();
                var intVal = function (i) {
                    return typeof i === 'string' ? i.replace(/[$,]/g, '') * 1 : typeof i === 'number' ? i : 0;
                };
                $.each($("#table-foot").find("th"),function(i,item){
                    if($(item).hasClass("isSum")){
                        var pageTotal = api.column(i, {page: 'current'}).data().reduce(function (a, b) {
                            return intVal(a) + intVal(b);
                        }, 0);
                        // 修改底部菜单
                        $(api.column(i).footer()).html(pageTotal.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + " m²");
                    }
                });
            }

        });
        $("#btn-search").on("click", table.draw);
    });
</script>