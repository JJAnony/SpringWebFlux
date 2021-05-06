import React, { Component } from 'react';
import CanvasJSReact from './assets/canvas.react';
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

var openValues = [{
    x: 0.222,
    y: new Date()
}, {
    x: 0.221,
    y: new Date()
}];

var closeValues = [{
    x: 0.122,
    y: new Date()
}, {
    x: 0.121,
    y: new Date()
}];

class LineChart extends Component {
    constructor() {
        super();
        this.updateChart = this.updateChart.bind(this);
    }

    componentDidMount() {
        setInterval(this.updateChart, 1000);
    }

    updateChart() {
        this.chart.render();
    }

    render() {
        const options = {
            title: {
                text: 'Stok Quotes'
            },
            axisY: {
                includeZero: false,
                valueFormatString: '0.##0',
                title: 'Price in (USD)'
            },
            axisX: {
                valueFormatString: 'HH:mm:ss',
            },
            data: [{
                type: 'line',
                name: 'Open value',
                showInLegend: true,
                yValueFormatString: '0.##0',
                xValueFormatString: 'HH:mm:ss',
                dataPoints: openValues
            }, {
                type: 'line',
                name: 'Close value',
                showInLegend: true,
                yValueFormatString: '0.##0',
                xValueFormatString: 'HH:mm:ss',
                dataPoints: closeValues
            }]
        };

        return (
            <div>
                <h1>Acompanhamento de Contação</h1>
                <CanvasJSChart options={options} onRef={ref => this.chart = ref} />
            </div>
        );
    }

}

export default LineChart;