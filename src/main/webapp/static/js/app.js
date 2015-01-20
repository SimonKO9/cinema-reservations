var app = angular.module('cinemaReservationsApp', ['ngRoute', 'cinemaControllers']);

app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
            .when('/movies', {
                templateUrl: 'static/partials/movie-list.html',
                controller: 'MovieListCtrl'
            })
            .when('/movies/:movieId/plays/:moviePlayId', {
                templateUrl: 'static/partials/movie-play-reservation.html',
                controller: 'ReservationController'
            })
            .otherwise({
                redirectTo: '/movies'
            });
    }]);
