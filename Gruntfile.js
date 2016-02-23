module.exports = function(grunt) {

	grunt
			.initConfig({
				bowercopy : {
					// Single file
					/*
					 * test : { options : { destPrefix :
					 * 'src/main/webapp/static' }, files : { 'js/jquery.min.js' :
					 * 'jquery/dist/jquery.min.js', 'js/angular.min.js' :
					 * 'angular/angular.min.js' } },
					 */
					// Entire folders
					folders : {
						files : {

							// font awesome
							'src/main/webapp/static/font-awesome/css' : 'font-awesome/css/font-awesome.min.css',
							'src/main/webapp/static/font-awesome/fonts' : 'font-awesome/fonts',
							
							'src/main/webapp/static/angular/angular-local-storage.min.js' : 'angular-local-storage/dist/angular-local-storage.min.js',
							'src/main/webapp/static/angular/angular.min.js' : 'angular/angular.min.js',
							'src/main/webapp/static/angular/angular-route.min.js' : 'angular-route/angular-route.min.js',
							'src/main/webapp/static/angular/ngMask.min.js' : 'ngMask/dist/ngMask.min.js',
							'src/main/webapp/static/angular/angular-resource.min.js' : 'angular-resource/angular-resource.min.js',
							'src/main/webapp/static/angular/angular-messages.min.js' : 'angular-messages/angular-messages.min.js',
							'src/main/webapp/static/angular/restangular.min.js' : 'restangular/dist/restangular.min.js',
							//'src/main/webapp/static/angular/lodash.min.js' : 'lodash/dist/lodash.min.js',

							// ui-bootstrap
							'src/main/webapp/static/angular/ui-bootstrap.min.js' : 'angular-bootstrap/ui-bootstrap.min.js',
							'src/main/webapp/static/angular/ui-bootstrap-tpls.min.js' : 'angular-bootstrap/ui-bootstrap-tpls.min.js',
							'src/main/webapp/static/angular/lodash.min.js' : 'angular-bootstrap/ui-bootstrap-csp.css',

							// checklist-model
							'src/main/webapp/static/angular/checklist-model.js' : 'checklist-model/checklist-model.js',

							// angular-animate
							'src/main/webapp/static/angular/angular-animate.min.js' : 'angular-animate/angular-animate.min.js',

							// pagination
							'src/main/webapp/static/angular/dirPagination.js' : 'angularUtils-pagination/dirPagination.js',
							'src/main/webapp/templates/dirPagination.tpl.html' : 'angularUtils-pagination/dirPagination.tpl.html',

							// ng-table
							'src/main/webapp/static/angular/ng-table.min.js' : 'ng-table/dist/ng-table.min.js',
							'src/main/webapp/static/angular/ng-table.min.css' : 'ng-table/dist/ng-table.min.css',

							// jQuery
							'src/main/webapp/static/jquery' : 'jquery/dist/jquery.min.js',


							'src/main/webapp/static/bootstrap/fonts' : 'bootstrap/dist/fonts',
							'src/main/webapp/static/bootstrap/css' : 'bootstrap/dist/css/bootstrap.min.css',
							'src/main/webapp/static/bootstrap/css' : 'bootstrap/dist/css/bootstrap-theme.min.css',
							'src/main/webapp/static/bootstrap/js' : 'bootstrap/dist/js/bootstrap.min.js'
								
						}
					}
				},
				includeSource : {
					options : {
						basePath : './src/main/webapp',
						baseUrl : '/'
					},
					myTarget : {
						files : {
							'./src/main/webapp/templates/index.html' : './src/main/webapp/templates/index.html'
						}
					}
				}
			});

	grunt.loadNpmTasks('grunt-bowercopy');
	grunt.loadNpmTasks('grunt-wiredep');
	grunt.loadNpmTasks('grunt-injector');
	grunt.loadNpmTasks('grunt-include-source');

	grunt.registerTask('default', [ 'wiredep' ]);
};