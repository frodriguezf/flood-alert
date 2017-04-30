var editor = require('serve-swagger-editor');
var app = editor({
    disableNewUserIntro: true,
    useBackendForStorage: true,
    useYamlBackend: true
}, 'src/extra/api.yaml');

var server = require('http').createServer(app);

server.listen(3333, function () {
    console.log('Do not close until you finish editing');
    console.log(server.address());
});
