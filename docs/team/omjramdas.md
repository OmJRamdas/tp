# Om J Ramdas - Project Portfolio Page

## Overview

Manage instrument rentals faster than a typical mouse/GUI driven app! Keep track of stock, user rental history, and
instrument-related finances in an all-in-one tracking app.

### Summary of Contributions

[Code contributed](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=OmJRamdas&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByAuthors&breakdown=true&checkedFileTypes=functional-code~test-code~docs&since=2025-04-07&tabOpen=true&tabType=authorship&tabAuthor=adoorknob&tabRepo=AY2425S2-CS2113-W11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

**Enhancements Implemented**
**Feature:** `Parser` functionality, handle input from `Ui` to choose respective command

* **Feature:** `CommandParser` functionality, interprets command pass back the respective parameters
* **Feature:** Added Command Handler functionality
    * Generic `Command`, `DefaultCommand`, `ExitCommand`, `HelpCommand`
    * Instrument Command: `addInstrumentCommand`, `DeleteCommand`, ect
* **Feature:** Added Finance functionality
    * `FinanceManager`
    * `FinanceStorage`
    * `FinanceCommand`
* **Feature:** `isOverDue` functionality to check if an instrument is overdue
    * Implemented a daily check
* **Enhancements:** `Ui` for main functionality
* **Enhancements:** Various cases of error handling
    * Commands
    * Finance
    * Instruments
* **Testing:** for relevant classes and methods
    * For all work done above as well as exceptions

**Contributions to UserGuide**

* Explanation of `Finance` commands and feature

**Contributions to DeveloperGuide**

* Overall Architecture diagram
* `Ui` diagram
* `Finance Manager` diagram
* `Parser` diagram

**Contributions to team-based tasks**

* Planned team meetings related and created issues for the team to solve

**Review contributions**

* Code review for other components
* Major Refactoring to make code better OOP wise




