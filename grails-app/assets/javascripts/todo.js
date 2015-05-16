angular.module('todo', [])
    .controller('TodoCtrl', ['$scope', '$http', function($scope, $http){
        $scope.user = {username: '', password: ''};
        $scope.result = undefined;
        $scope.showLogin = false;
        //var headers = { 'X-Auth-Token': '' };
        var headers = { 'Authorization': '' };

        $scope.todos = function(){
            console.log("headers: headers:: ", headers);
            console.log("headers: $scope.result:: ", $scope.result);
            $http.get('http://localhost:8080/springRest/api/todo/list', {headers: headers})
                .success(function(data){
                    console.log("success:data: ", data);
                    $scope.result = data;
                })
                .error(function(error, status){
                    console.log("error:data: ", error);
                    console.log("error:status: ", status);
                    if(status != 200) {
                        $scope.showLogin = true;
                        $scope.result = "Not able to get resource for unauthorized user. Check Dev Tool for details.";
                    }
                });
        };

        $scope.authenticate = function(){

            $http.post('http://localhost:8080/springRest/api/login',
                {username: $scope.user.username, password: $scope.user.password})
                .success(function(result){
                    $scope.result = result;
                    headers['Authorization'] = "Bearer "+result.access_token;
                    console.log("headers: headers:: ", headers);
                    console.log("headers: $scope.result:: ", $scope.result);
                })
                .error(function(error){
                    $scope.result = error;
                });
        }
    }])
    .directive("pop", function(){
        return {
            scope: false,
            link: function($scope, element){
                var options = {
                    container: 'body',
                    html: true,
                    content: "Click again to access resource<br>(now using the generated token)"
                };

                $scope.$watch('result', function(value){
                    if(value && value.access_token){
                        $(element).popover(options);
                        $(element).popover('show');
                    }
                });
            }
        }
    });