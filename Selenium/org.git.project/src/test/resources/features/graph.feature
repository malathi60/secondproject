Feature: validating emi amount
Scenario:  verify amount from graph and table
Given open browser using "chrome"
Then launch site "url1"
Then move to required svg element graph
Then validate "Interest" amount from table and graph
And close site