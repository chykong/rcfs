<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<c:if test="${bln:isP('PrjPreallocationDeleteBatch')}">
<label class="my-tooltip-link tooltip-error" title="全选数据过多时，可能有少许卡顿" style="margin-left: 14px">
    <input id="all_check" type="checkbox" class="ace">
    <span class="lbl">全选</span>
</label>
<button class="btn btn-danger btn-sm" id="btnBatch" style="margin-left: 10px">
    <i class="ace-icon fa fa-trash-o"></i> 批量删除
</button>
</c:if>
<table id="basic-table" class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th>序号</th>
        <th>编号</th>
        <th>被拆除腾退人</th>
        <th>房屋坐落</th>
        <th>实际用地面积</th>
        <th>总建筑面积</th>
        <th>06年前面积</th>
        <th>06-09年间面积</th>
        <th>
            <c:if test="${prj_base_info_id != 28}">
                09年后面积
            </c:if>
            <c:if test="${prj_base_info_id == 28}">
                09-13年间面积
            </c:if>
        </th>
        <c:if test="${prj_base_info_id == 28}">
            <th>13年后面积</th>
        </c:if>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody id="tbody">

    </tbody>
    <tfoot id="table-foot">
    <tr>
        <th>合计：</th>
        <th></th>
        <th></th>
        <th></th>
        <th class="isSum"></th>
        <th class="isSum"></th>
        <th class="isSum"></th>
        <th class="isSum"></th>
        <th class="isSum"></th>
        <c:if test="${prj_base_info_id == 28}">
            <th></th>
        </c:if>
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
                    async: true,
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
                        content: $("#content").val(),
                        page: parseInt(data.start / data.length) + 1,
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
                    width: "60px",
                    render: function (data) {
                        return data || "";
                    }
                }, {
                    data: "map_id",
                    width: "80px",
                    render: function (data) {
                        return "<span data-id='" + data + "'>" + data + "</span>" || "";
                    }
                },
                {
                    data: "host_name",
                    width: "80px",
                    render: function (data, type, row) {
                        return '<a \
                            href="<c:url value="/prj/preallocation/basic/view.htm"/>?id=' + row.id + '">' + data + '</a>';
                    }
                },
                {
                    data: "location",
                    width: "120px",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "cog_land_area",
                    width: "80px",
                    sClass: "text-right",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "total_homestead_area",
                    width: "80px",
                    sClass: "text-right",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "before_area",
                    width: "80px", sClass: "text-right",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "between_area",
                    width: "90px", sClass: "text-right",
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: "after_area",
                    width: "100px", sClass: "text-right",
                    render: function (data) {
                        return data || "";
                    }
                },
            <c:if test="${prj_base_info_id == 28}">
                {
                    data: "last_area",
                    width: "100px", sClass: "text-right",
                    render: function (data) {
                        return data || "";
                    }
                },
            </c:if>
                {
                    data: "status",
                    width: "80px",
                    render: function (data) {
                        switch (data) {
                            case 0:
                                return '<span class="label label-default ">未入户</span>';
                            case 10:
                                return '<span class="label label-default ">已入户未签约</span>';
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
                    width: "140px",
                    render: function (data, type, row) {
                        var retHtml = '';
                        if (row.status != 70 && ${bln:isP('PrjPreallocationUpdate')}) {
                            retHtml += '<a class="btn-sm btn-info" href="javascript:updateBasic(' + data + ')"><i class="ace-icon fa fa-pencil-square-o "></i>修改</a>';
                        }
                        retHtml += " ";
                        if (${bln:isP('PrjPreallocationDelete')}) {
                            retHtml += '<a class="btn-sm btn-danger" href="javascript:delBasic(' + data + ')"><i class="ace-icon fa fa-trash-o "></i>删除</a>';
                        }
                        return retHtml;
                    }
                }
            ],
            fixedColumns: {
                leftColumns: 2,
                rightColumns: 1
            },
            language: {
                url: '<c:url value="/assets/datatables/i18n/Chinese.json"/>'
            },
            fnDrawCallback: function () {
                var data = this.api().column(1).data();
                var name = this.api().column(2).data();
                this.api().column(0).nodes().each(function (cell, i) {
                    var index = parseInt(i) + 1;

                    cell.innerHTML = '<label><input name="deletes" data-name="' + name[i] + '" type="checkbox" value="' + data[i] + '" class="ace">' +
                        '<span class="lbl"></span></label><span class="number">' + index + '</span>';
//                    cell.innerHTML =  "<input type='checkbox' value='' />" +index ;
                });
            },
            footerCallback: function (tfoot, data) {
                var api = this.api();
                var intVal = function (i) {
                    return typeof i === 'string' ? i.replace(/[$,]/g, '') * 1 : typeof i === 'number' ? i : 0;
                };
                $.each($("#table-foot").find("th"), function (i, item) {
                    if ($(item).hasClass("isSum")) {
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
            if (times == 2) {
                if ($table_id.width() < $('#basic-table_wrapper').width()) {
                    $('.DTFC_RightWrapper').remove();
                }
                $('#table-foot').remove();
//                $("#foo").addClass('hidden');
            }
        });

        $("#btn-search").on("click", table.draw);
    });
</script>