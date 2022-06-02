Feature: Company Registration

  A company must register in order to showcase their application on the site.

  Scenario Outline: Mandatory Fields
    Given David wants to showcase his application
    When he submits his application without completing the <Field> field
    Then he should be presented with the error message "<Error Message>"
    Examples:
      | Field                          | Error Message           |
      | Name of Company Representative | This field is required. |
      | Designation                    | This field is required. |
      | Email                          |  This field is required. |

