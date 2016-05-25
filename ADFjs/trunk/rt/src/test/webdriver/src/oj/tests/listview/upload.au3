$count =0
While $count <> 10
   $hd1=WinActivate("File Upload")
   If $hd1 <> 0 Then
	  ControlFocus("File Upload", "","Edit1")
	  Sleep(500)
	  ControlSetText"File Upload", "","Edit1", "D:\\personal\\RituSoniaYash\\Ritu\\Ritu_Bapna_Resume.doc")
	  Sleep(500)
	  ControlClick("File Upload", "","Button1")
	  Sleep(500)
	  Exit
   EndIf
   Sleep(500)
   $count=$count+1
   WEnd