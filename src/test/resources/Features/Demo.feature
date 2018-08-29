@smoke
Feature: Calculations

  Background Given user open calculator

  @add
  Scenario Outline: Addition
    When user tap ADD button <count> times
    Then the result should be <count>

    Examples:
      | count |
      | 1		|
      | 3		|

  @sub
  Scenario Outline: Subtraction
    When user tap SUBTRACT button <count> times
    Then the result should be <result>

    Examples:
      | count | result |
      | 1		| -1	 |
      | 3		| -3	 |

  @sqr
  Scenario Outline: Square root
    When user tap ADD button <add count> times
    And user tap SUBTRACT button <sub count> times
    And user tap SQUARE ROOT button <square count> times
    Then the result should be <result>

    Examples:
      | add count | sub count | square count | result |
      | 4			| 	0		| 1		 	   | 2		|
     #	| 0			| 	1		| 1	 		   | 0		|
      | 0			| 	0		| 3	 		   | 0		|

  @div
  Scenario Outline: Division
    When user tap ADD button <add count> times
    And user tap SUBTRACT button <sub count> times
    And user tap DIVIDE by 2 button <div count> times
    Then the result should be <result>

    Examples:
      | add count | sub count | div count | result |
      | 4			| 0			| 2  	 	| 1		 |
      | 0			| 4			| 2			| -1	 |
      | 3			| 0			| 1			| 1.5	 |
#	| 0			| 0			| 1			| 0		 |

  @mult
  Scenario Outline: Multiply
    When user tap ADD button <add count> times
    And user tap SUBTRACT button <sub count> times
    And user tap MULTIPLY by 2 button <mult count> times
    Then the result should be <result>

    Examples:
      | add count | sub count | mult count | result |
      | 4			| 0			| 1  	 	 | 8	  |
      | 2			| 0			| 2			 | 8	  |
      | 0			| 4			| 3			 | -32	  |

  @power
  Scenario Outline: Power
    When user tap ADD button <add count> times
    And user tap SUBTRACT button <sub count> times
    And user tap POWER by 2 button <power count> times
    Then the result should be <result>

    Examples:
      | add count | sub count | power count | result |
      | 2			| 0			| 1  	 	  | 4	   |
      | 0			| 2			| 1			  | 4	   |
      | 3			| 0			| 2			  | 81	   |