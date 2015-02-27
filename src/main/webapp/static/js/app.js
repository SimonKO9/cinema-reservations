var app = angular.module('cinemaReservationsApp', ['ngRoute', 'cinemaControllers']);

app.config(['$routeProvider', '$httpProvider',
    function ($routeProvider, $httpProvider) {
        $routeProvider
            .when('/movies', {
                templateUrl: 'static/partials/movie-list.html',
                controller: 'MovieListCtrl'
            })
            .when('/movies/:movieId/plays/:moviePlayId', {
                templateUrl: 'static/partials/movie-play-reservation.html',
                controller: 'ReservationController'
            })
            .when('/admin/login', {
                templateUrl: 'static/partials/admin-login.html',
                controller: 'LoginController'
            })
            .when('/reservations', {
                templateUrl: 'static/partials/admin-reservations.html',
                controller: 'ListReservationsController',
                resolve: {
                    User: function () {
                        var userJson = sessionStorage.getItem('userData');
                        var  userToken = sessionStorage.getItem('userToken');
                        if (userJson != null && userToken != null) {
                            $httpProvider.defaults.headers.common.Authorization = 'Basic ' + userToken;
                            return JSON.parse(userJson);
                        }
                        else throw "NOT_LOGGED_IN";
                    }
                }
            })
            .otherwise({
                redirectTo: '/movies'
            });
    }]);

app.run(function ($rootScope, $location) {
    $rootScope.$on('$routeChangeError', function () {
        $location.path('/admin/login');
    });
});