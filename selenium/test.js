/**
 * Created by abe23 on 10/01/17.
 */


var chai = require('chai');
var expect = chai.expect;



describe('Testing the application', function(){
    var selenium;
    var selenuimProcess;
    var client;



    before(function(done) {

        //Start the selenium server
        selenium = require('selenium-standalone');
        selenium.install({
                // check for more recent versions of selenium here:
                // https://selenium-release.storage.googleapis.com/index.html
                version: '3.0.1',
                baseURL: 'https://selenium-release.storage.googleapis.com'

            },function() {

                selenium.start({
                    spawnOptions: {
                        //don't show the logging output of the server... We are not interested in this.
                        stdio: 'ignore'
                    },
                    version: '3.0.1'
                }, function(err, child) {
                    //we need this to stop the process againd
                    selenuimProcess = child;
                    console.log("Started seleniumserver");
                    // Use webdriverjs to create a Selenium Client
                    try {
                        client = require('webdriverio').remote({
                            desiredCapabilities: {
                                // You may choose other browsers
                                // http://code.google.com/p/selenium/wiki/DesiredCapabilities
                                browserName: 'phantomjs',
                                pageLoadStrategy: 'eager'
                            },
                            // webdriverjs has a lot of output which is generally useless
                            // However, if anything goes wrong, remove this to see more details
                            logLevel: 'silent'
                        });
                        client = client.init();
                        client.url('http://localhost:8090/rentalapp_war.gradle').then(function () {
                            done();
                        });
                    } catch (e) {
                        console.log(e);
                    }
                })
            }
        );
    });


    describe('Check homepage', function() {

        it('Should be able to see the page', function (done) {
            //client.pause(500);
            client.getTitle().then(function (title) {
                expect(title).to.have.string('Homepage');
                done();
            });
        });

        it('Should login', function(done) {
            client.click('a[href="login.html"]').then(function (e) {
                //var input = $('#username');
                client.setValue("#username","testowner");
                client.setValue("#password","testing");

                client.pause(1000).click('input[type="submit"]').then(function (e) {
                    done();
                });
            }).pause(100);
        })

    }
    );

    after(function(done) {
        client.end();
        selenuimProcess.kill();
        done();
    });
});


