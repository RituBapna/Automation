$count=0
While $count <> 10
 $hd1=WinActivate("Open")
   if $hd1 <> 0 Then

	  ControlFocus ("Open", "", "Edit1")
	  Sleep(500)
	  ControlSetText("Open", "", "Edit1", "D:\personal\RituSoniaYash\Ritu\Ritu_Bapna_Resume.doc")
	  Sleep(500)
	  ControlClick("Open", "", "Button1")
	  Exit
  EndIf
  Sleep(1000)
  $count= $count+1
WEnd
