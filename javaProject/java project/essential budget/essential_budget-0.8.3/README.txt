Welcome to Essential Budget 0.8.3

== Starting Essential Budget ==

In most cases, if you have a Java Runtime Environment installed on
your computer, you will be able to simply run the startup script
provided that is appropriate for your platform.  This will be either
"essentialbudget" or "essentialbudget.bat" depending on if you are
using Linux or Windows, respectively.

However, if you are using Essential Budget on Windows, you may
encounter difficulties starting Essential Budget if your "java"
command is not installed on your operating system path.  In this case,
simply edit the "essentialbudget.bat" file and change the line that
reads:

set JAVA=java

to include the full location of your Java interpreter.  For example,
if your copy of Java is installed in "c:\jre", you should change this
line to read:

set JAVA=c:\jre\bin\java

== Using Essential Budget ==

In order to use Essential Budget, you need to understand the following
terminology that Essential Budget uses.

An "asset account" is a place where you store money such as a bank
account or cash on hand.  It is also a place that has value you can
trade for money like the equity you have in your house if you own one.

A "liability account" is somewhere you owe money.  Typically this will
be your credit card accounts, plus perhaps a mortgage on your house or
an automobile loan.

An "inflow category" represents how you earn your money.  Typical
inflow categories would include "Husband's job", "Wife's job", or
"Interest income".

An "outflow category" represents the ways you spend money.  For
example, "food", "clothes", "transportation", "recreation", etc.

Inflow and outflow categories together make up your budget
categories.  Setting up a budget basically involves telling Essential
Budget how much you intend to earn or spend each month in each of your
inflow and outflow categories.

Once you've told Essential Budget what you expect to earn or spend,
you can then tell it about occasions where you've actually earned or
spent money on the "Transactions" view.  When you enter the amount
into either the inflow or outflow field, the "categories" list box at
the bottom of the view will be populated with a list of applicable
categories.  Double-clicking a category allows you to assign actual
income or expenses to inflow or outflow categories.  The amounts you
assign will then automatically be propogated back to the budget view
and the balance fields on the Accounts/Categories perspective.

