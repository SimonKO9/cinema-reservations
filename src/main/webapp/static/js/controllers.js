angular.module('cinemaControllers', [])
    .controller('MovieListCtrl', function ($scope, $http) {
        $scope.movies = [];

        function loadMoviePlays(movie) {
            $http.get('api/movies/' + movie.id + '/plays')
                .success(function (data, status, headers, cfg) {
                    var moviePlays = angular.copy(data).map(function (play) {
                        play = angular.copy(play);
                        play.playDateObject = new Date(play.playDate);
                        return play;
                    });
                    movie.plays = moviePlays;
                })
                .error(function (data, status) {
                    alert("Error. Please try again later.");
                });
        }

        $http.get('api/movies')
            .success(function (data, status, headers, cfg) {
                $scope.movies = angular.copy(data);
                $scope.movies.forEach(loadMoviePlays);
                console.log($scope.movies);
            })
            .error(function (data, status) {
                alert("Error. Please try again later.");
            });
    })

    .controller('ReservationController', function ($scope, $http, $routeParams) {
        var movieId = $routeParams.movieId;
        var moviePlayId = $routeParams.moviePlayId;

        (function () {
            $scope.step = 'FORM';
            $scope.reservation = {
                seats: 2,
                email: ''
            };
        })();

        $scope.doReservation = function() {
            $scope.statusMsg = '';
            var reservation = {
                seatsTaken: $scope.reservation.seats,
                email: $scope.reservation.email
            };
            $http.post('api/movies/' + movieId + '/plays/' + moviePlayId + '/reservations', reservation)
                .success(function (data) {
                    $scope.reservation = angular.copy(data);
                    $scope.step = 'CONFIRMATION';
                })
                .error(function (data, status, headers) {
                    $scope.statusMsg = data.message;
                });
        };

        function loadMoviePlay() {
            $http.get('api/movies/' + movieId + '/plays/' + moviePlayId)
                .success(function (play) {
                    $scope.movie.play = play;
                });
        }

        $http.get('api/movies/' + movieId)
            .success(function (data) {
                $scope.movie = angular.copy(data);
                loadMoviePlay();
            });

    });

