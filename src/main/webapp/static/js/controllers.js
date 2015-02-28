angular.module('cinemaControllers', ['angular-loading-bar'])
    .config(function(cfpLoadingBarProvider) {
        cfpLoadingBarProvider.includeSpinner = true;
    })
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

        $scope.doReservation = function () {
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

    })

    .controller('LoginController', function ($scope, $http, $location) {

        $scope.model = {username: '', password: ''};
        $scope.errorMsg = '';

        $scope.signIn = function (username, password) {
            var token = btoa(username + ":" + password);

            $http.defaults.headers.common.Authorization = 'Basic ' + token;

            $http({
                method: 'GET',
                url: 'api/users/' + username
            })
                .success(function (data) {
                    sessionStorage.setItem('userData', JSON.stringify(data));
                    sessionStorage.setItem('userToken', token);
                    $location.path('/reservations')
                })
                .error(function (data, status, headers) {
                    if (status / 100 == 4) {
                        $scope.errorMsg = 'Invalid credentials or insufficient privileges.';
                    } else {
                        $scope.errorMsg = 'There was an error, please try again later.';
                    }
                });
        }

    })

    .controller('ListReservationsController', function ($scope, $http, $filter, User, cfpLoadingBar) {
        $scope.searchModel = {
            movieTitle: '',
            hallKey: '',
            dateFrom: $filter('date')(new Date() - 3600 * 24 * 1000, 'yyyy-MM-dd'),
            dateTo: $filter('date')(new Date(), 'yyyy-MM-dd'),
            reservationId: '',
            email: ''
        };

        $scope.userData = User;
        $scope.searchResults = [];

        $scope.search = function () {
            var searchParameters = angular.copy($scope.searchModel);
            if(searchParameters.dateFrom) {
                searchParameters.dateFrom = new Date(searchParameters.dateFrom).getTime();
            }
            if(searchParameters.dateTo) {
                searchParameters.dateTo = new Date(searchParameters.dateTo).getTime();
            }
            
            cfpLoadingBar.start();
            $http
                .get('/api/reservations', {
                    params: searchParameters
                })
                .success(function (data) {
                    $scope.searchResults = data;
                    cfpLoadingBar.complete();
                })
                .error(function (data) {
                    $scope.searchResults = [];
                    cfpLoadingBar.complete();
                });
        }
    })
;

