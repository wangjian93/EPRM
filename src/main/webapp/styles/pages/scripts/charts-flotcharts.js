var plot;
var pageviews;
var visitors;
var ChartsFlotcharts = function() {

    return {
        //main function to initiate the module

        init: function() {

        },

        initCharts: function() {

            if (!jQuery.plot) {
                return;
            }

            var data = [];
            var totalPoints = 250;

            // random data generator for plot charts

            function getRandomData() {
                if (data.length > 0) data = data.slice(1);
                // do a random walk
                while (data.length < totalPoints) {
                    var prev = data.length > 0 ? data[data.length - 1] : 50;
                    var y = prev + Math.random() * 10 - 5;
                    if (y < 0) y = 0;
                    if (y > 100) y = 100;
                    data.push(y);
                }
                // zip the generated y values with the x values
                var res = [];
                for (var i = 0; i < data.length; ++i) {
                    res.push([i, data[i]]);
                }

                return res;
            }

            //Interactive Chart

            function chart2() {
                if ($('#chart_2').size() != 1) {
                    return;
                }

                function randValue() {
                    var s = (Math.floor(Math.random() * (1 + 40 - 20))) + 20;
                    if(s>100){
                    		s=90;
                    }
                    return s;
                }
                pageviews = [
                    [1, 50],
                    [2, 50],
                    [3, 50],
                    [4, 50],
                    [5, 50],
                    [6, 50],
                    [7, 50],
                    [8, 50],
                    [9, 50],
                    [10, 50],
                    [11, 50],
                    [12, 50],
                    [13, 50],
                    [14, 50],
                    [15, 50],
                    [16, 50],
                    [17, 50],
                    [18, 50],
                    [19, 50],
                    [20, 50],
                    [21, 50],
                    [22, 50],
                    [23, 50],
                    [24, 50],
                    [25, 50],
                    [26, 50],
                    [27, 50],
                    [28, 50],
                    [29, 50],
                    [30, 50],
                    [30, 50]
                ];
               visitors = [
                    [1, 50],
                    [2, 50],
                    [3, 50],
                    [4, 50],
                    [5, 50],
                    [6, 50],
                    [7, 50],
                    [8, 50],
                    [9, 50],
                    [10, 50],
                    [11, 50],
                    [12, 50],
                    [13, 50],
                    [14, 50],
                    [15, 50],
                    [16, 50],
                    [17, 50],
                    [18, 50],
                    [19, 50],
                    [20, 50],
                    [21, 50],
                    [22, 50],
                    [23, 50],
                    [24, 50],
                    [25, 50],
                    [26, 50],
                    [27, 50],
                    [28, 50],
                    [29, 50],
                    [30, 50],
                    [31, 50]
                ];

                plot = $.plot($("#chart_2"), [{
                    data: pageviews,
                    label: "妥善率",
                    lines: {
                        lineWidth: 2,
                    },
                    shadowSize: 0

                }, {
                    data: visitors,
                    label: "Spec标准",
                    lines: {
                        lineWidth: 1,
                    },
                    shadowSize: 0
                }], {
                    series: {
                        lines: {
                            show: true,
                            lineWidth: 2,
                            fill: true,
                            fillColor: {
                                colors: [{
                                    opacity: 0.05
                                }, {
                                    opacity: 0.01
                                }]
                            }
                        },
                        points: {
                            show: true,
                            radius: 3,
                            lineWidth: 1
                        },
                        shadowSize: 2
                    },
                    grid: {
                        hoverable: true,
                        clickable: true,
                        tickColor: "#eee",
                        borderColor: "#eee",
                        borderWidth: 1
                    },
                    colors: ["#d12610", "#37b7f3", "#52e136"],
                    xaxis: {
                        ticks: 31,
                        tickDecimals: 0,
                        tickColor: "#eee",
                    },
                    yaxis: {
                    		max:100,
                    	    min: 0,//最小刻度
                        tickSize: 5,//递增量
                        ticks: 21,
                        tickDecimals: 0,
                        tickColor: "#eee",
                    }
                });


                function showTooltip(x, y, contents, x_) {
	                	var c = contents + "<br>" + "异常:" + "<br>";
                		$("#abnormal").children("tbody").find("tr").each(function () {
                			var date = $(this).children('td:eq(0)').text();
                			date = date.substring(8);
                			var str = String(x_);
                			if(str.length==1) str = "0" + x_;
                			console.log(str + "   " + date );
                			if(str == date){
                				c += $(this).children('td:eq(1)').text() + ": " + $(this).children('td:eq(2)').text() + "<br>";
                			}
                		});
                		
                    $('<div id="tooltip">' + c + '</div>').css({
                        position: 'absolute',
                        display: 'none',
                        top: y + 5,
                        left: x + 15,
                        //right: x+15,
                        border: '1px solid #333',
                        padding: '4px',
                        color: '#fff',
                        'border-radius': '3px',
                        'background-color': '#333',
                        opacity: 0.80
                    }).appendTo("body").fadeIn(200);
                }

                var previousPoint = null;
                $("#chart_2").bind("plothover", function(event, pos, item) {
                    $("#x").text(pos.x.toFixed(2));
                    $("#y").text(pos.y.toFixed(2));

                    if (item) {
                        if (previousPoint != item.dataIndex) {
                            previousPoint = item.dataIndex;

                            $("#tooltip").remove();
                            var x = item.datapoint[0].toFixed(0),
                                y = item.datapoint[1].toFixed(0);

                            showTooltip(item.pageX, item.pageY, item.series.label + " : " + y + "%", x);
                        }
                    } else {
                        $("#tooltip").remove();
                        previousPoint = null;
                    }
                });
            }

            chart2();
        },

        initBarCharts: function() {

        },

        initPieCharts: function() {

        },

        initAxisLabelsPlugin: function() {
        	
        }
    };
}();
