<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="layui-footer"  style="height: 30px">
    <div class="layui-row" style="height: 30px" >
        <p style="font-size: 10px;color:#2E2D3C;text-align: center;line-height: 30px">
            <i class="layui-icon" style="font-size: 10px;">&#xe672;</i>
            <span onclick="tips(this)">技术支持：陈天飞</span>
        </p>
    </div>
</div>

<script>
    //功能未启用提示
    function tips(obj) {
        layui.use(['layer'], function () {
                var layer = layui.layer;
                layer.tips('拍了拍我？？？是遇到问题了吗？微信：dontlikerain_xyt',obj,{
                    tips: [1, '#4891e5']
                }
            );
            }
        )
    }
</script>
