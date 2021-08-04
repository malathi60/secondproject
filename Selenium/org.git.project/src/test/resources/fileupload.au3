#Region ;**** Directives created by AutoIt3Wrapper_GUI ****
#AutoIt3Wrapper_Compile_Both=y
#AutoIt3Wrapper_UseX64=y
#EndRegion ;**** Directives created by AutoIt3Wrapper_GUI ****
WinWait("Open"," ",5000)
ControlFocus("Open","","Edit1")
Sleep(2000)
ControlSetText("Open","","Edit1","C:\Users\Dell\Pictures\images.jpg")
Sleep(2000)
ControlClick("Open","","Button1")