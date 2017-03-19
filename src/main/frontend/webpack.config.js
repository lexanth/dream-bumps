const path = require('path');
const merge = require('webpack-merge');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');

const TARGET = process.env.npm_lifecycle_event;
const PATHS = {
  source: path.join(__dirname, 'src'),
  output: path.join(__dirname, 'target')
};

const common = {
  entry: [
    PATHS.source
  ],
  output: {
    path: PATHS.output,
    publicPath: '/',
    filename: 'bundle.js'
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: 'src/index.ejs',
      minify: {
        removeComments: true,
        collapseWhitespace: true
      },
      inject: true
    })
  ],
  module: {
    rules: [
          {test: /\.jsx?$/, exclude: /node_modules/, loaders: ['babel-loader']},
          {test: /\.eot(\?v=\d+.\d+.\d+)?$/, loader: 'file-loader'},
          {test: /\.woff(2)?(\?v=[0-9]\.[0-9]\.[0-9])?$/, loader: 'url-loader?limit=10000&mimetype=application/font-woff'},
          {test: /\.[ot]tf(\?v=\d+.\d+.\d+)?$/, loader: 'url-loader?limit=10000&mimetype=application/octet-stream'},
          {test: /\.svg(\?v=\d+\.\d+\.\d+)?$/, loader: 'url-loader?limit=10000&mimetype=image/svg+xml'},
          {test: /\.(jpe?g|png|gif)$/i, loader: 'file-loader?name=[name].[ext]'},
          {test: /\.ico$/, loader: 'file-loader?name=[name].[ext]'},
          {test: /(\.css|\.scss|\.sass)$/, loaders: ['style-loader', 'css-loader?sourceMap', 'sass-loader?sourceMap']}
        ]
      }
};

// if (TARGET === 'start' || !TARGET) {
  module.exports = merge(common, {
    devServer: {
      port: 9090,
      proxy: [{
        context: ['/api', '/swagger-resources', '/v2/api-docs'],
        target: 'http://localhost:8080',
        secure: false,
        prependPath: false
      }],
      publicPath: 'http://localhost:9090/',
      historyApiFallback: true
    },
    devtool: 'source-map'
  });
// }

// if (TARGET === 'build') {
//   module.exports = merge(common, {});
// }
