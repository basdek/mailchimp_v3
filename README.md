# mailchimp_v3

[![Software License][ico-license]](LICENSE.md)
[![Build Status][ico-travis]](https://travis-ci.org/basdek/mailchimp_v3)

A Scala (Java compatible) implementation of the MailChimp api, version 3.

## Installation and Usage

Currently not available in a distributed manner via either maven or sbt.
(Will be in maven, sbt in the future.)

In development, certainly not production ready yet.

**Requires a JDK of version 8 or above (because of external dependencies).**

### Usage

The usage of the library is fairly simple:

1. Instantiate a Config object with an api key and your endpoint
    ```usX.api.mailchimp.com/v3``` (please note: NO trailing slash;
    NO protocol specification please.)
2. Pass the Config object to any operations you want to execute. Enjoy.

## Testing

* We use [scalatest](http://www.scalatest.org) for testing.

## Contributing

Pull requests are welcomed and credit will be given where due.
Please do include tests with your PR.

* Please respect the 80 character (soft) limit (wherever reasonable.)
* Identation is done by spaces (2 spaces in scala sourcecode.)

## License
Licensed under the generally permissive MIT license.

Consult the [license file](LICENSE.md) for more information.

## Notes

### General

* We follow semantic versioning guidelines. ([SemVer v2.0.0](http://semver.org/))

### Library / API specific

* Currently only the basic auth variant of the available authentication methods
provided by MailChimp is implemented. Future versions will support both.

### Legal

* All trademarks, service marks, trade names, trade dress, product names appearing
in this library's (source and documentation) are the property of their respective
owners.

### Changelog

#### 0.2.0

* Improving usability of the lib in Java:
    * It is fairly complicated to instantiate a ```None``` in Java, which makes
    the DTO case classes as they are right now pretty useless. Also, the default
    arguments for ```ReadOnlyField```s are not respected in Java. Therefore added
    constructor methods to the case classes that are more Java friendly.

#### 0.1.0

* Set up of basic project structure:
    * Instantiate a Config
    * Load it in any operation you want to execute
    * Execute the operations and enjoy your MailChimp communication.
    * For tests
        * ENV-var based API contact (for integration tests)
* Implemented a credential testing operation
* Implemented the ```GET /lists``` operation (currently ignoring the modules field.)
* Implemented the ```GET /list/{list_id}``` operation (in the same manner as
mentioned for ```/lists```)
* Implemented the ```POST /lists``` operation
* Implemented the ```GET /lists/{list_id}/members``` operation
* Implemented the ```POST /lists/{list_id}/members``` operation




[ico-license]: https://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat-square
[ico-travis]: https://img.shields.io/travis/basdek/mailchimp_v3/master.svg?style=flat-square