<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<table id="basic-table" class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th>序号</th>
        <th>编号</th>
        <th>被拆除腾退人</th>
        <th>坐落</th>
        <th>总建筑面积</th>
        <th>入户日期</th>
        <th>签约日期</th>
        <th>交房日期</th>
        <th>拆除日期</th>
        <th>审核日期</th>
        <th>放款日期</th>
        <th>备注</th>
        <th>操作</th>
    </tr>
    </thead>
    <tfoot id="table-foot">
    <tr>
        <th>合计：</th>
        <th></th>
        <th></th>
        <th></th>
        <th class="isSum"></th>
        <th></th>
        <th></th>
        <th></th>
        <th></th>
        <th></th>
        <th></th>
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
            scrollY: '300px',
            paginate: false, //翻页功能
            sortable: false,
            lengthChange: true,
            lengthMenu: [15],
            processing: true,
            serverSide: true,
            ajax: function (data, callback, settings) {
                $.ajax({
                    type: "GET",
                    url: '<c:url value="/prj/preallocation/basic/getPreallocation.htm"/>',
                    data: {
                        map_id: $("#map_id").val(),
                        host_name: $("#host_name").val(),
                        location: $("#location").val(),
                        section: $("#section").val(),
                        groups: $("#groups").val(),
                        town: $("#town").val(),
                        village: $("#village").val(),
                        status: $("#status").val(),
                        search_date: $("#search_date").val(),
                        search_blank: $("#search_blank").val(),
                        page: parseInt(data.start/data.length) + 1,
                        size: data.length
                    },
                    cache: false,  //禁用缓存
                    dataType: "json",
                    success: function (result) {
                        result.draw = result.searchDTO.draw;
                        callback(result);
                    }
                });
            },
            //使用对象数组，一定要配置columns，告诉 DataTables 每列对应的属性
            //data 这里是固定不变的，id，name，age，sex等为你数据里对应的属性
            columns: [
                {
                    data: "map_id",
                    width: "40px",
                    render: function (data) {
                        return data || "";
                    }
                },{
                    data: "map_id",
                    width: "80px",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "host_name",
                    width: "80px",
                    render: function (data, type, row) {
                        return '<a \
                            href="<c:url value="/prj/preallocation/basic/view.htm"/>?id=' + row.id + '">'+ data+ '</a>';
                    }
                },
                {
                    data: "location",
                    width: "200px",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "total_homestead_area",
                    width: "90px",sClass: "text-right",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "in_host_date",
                    width: "120px",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "signed_date",
                    width: "100px",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "handover_house_date",
                    width: "100px",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "demolished_date",
                    width: "120px",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "audit_date",
                    width: "100px",
                    render: function (data) {
                        return data || "";
                    }
                },{
                    data: "money_date",
                    width: "100px",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "remarks",
                    width: "120px",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "id",
                    width: "80px",
                    render: function (data,type,row) {
                        if (row.status != 70 && ${bln:isP('PrjPreallocationUpdate')}) {
                                return '<a class="btn-sm btn-info" href="<c:url value="/prj/preallocation/basic/toUpdate.htm?backUrl=${backUrl}&type=1&id="/>' + data + '">\
                                    <i class="ace-icon fa fa-pencil-square-o "></i>修改</a>';
                        } else {
                            return '';
                        }
                    }
                }
            ],
            fixedColumns: {
                leftColumns: 2,
                rightColumns:1
            },
            language: {
                url: '<c:url value="/assets/datatables/i18n/Chinese.json"/>'
            },
            fnDrawCallback : function(){
                this.api().column(0).nodes().each(function(cell, i) {
                    cell.innerHTML =  i + 1;
                });
            },
            footerCallback: function (footer,data) {
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
                $(api.column(1).footer()).html(data.length + "户");
                $(".DTFC_LeftFootWrapper").remove();

            }

        });
        var times = 0;
        table.on('draw.dt', function (e) {
            times++;
            console.log(times);
            if(times == 2){
                if($table_id.width() < $('#basic-table_wrapper').width()){
                    $('.DTFC_RightWrapper').remove();
                }
                $("#foo").addClass('hidden');
            }
        } );
        $("#btn-search").on("click", table.draw);
    });
</script>