Email_List = "jitendra.sawant@worldline.com"

Set App = CreateObject("Outlook.Application")
Set Mail = App.CreateItem(0)

With Mail
    .To = Email_List
    .CC = "jitendra.sawant@worldline.com"
    .BCC = "sawant.jitendra0@gmail.com"
    .Subject = "Automation Execution reports"
    .HTMLBody = "Axis API Automation Batch Run TestResults"
    '.Body = strbody
    'You can add a file like this
    .Attachments.Add ("D:\jitu\selenium project\Test\Extendedreport\ExtendedReport.html")

    'use .Send (to send) or .Display (to display the email and edit before sending)
    .Display
    .send
End With

Set Mail = Nothing
Set App = Nothing