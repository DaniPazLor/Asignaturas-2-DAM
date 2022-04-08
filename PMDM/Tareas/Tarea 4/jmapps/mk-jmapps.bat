REM %W% %E%
REM Sample build script for JMStudio, JMFRegistry on Windows.

REM set where JDK is.  Requires JDK 1.1.* or above.
set JAVAHOME=e:\jdk1.2.2

REM set where JMF is
set JMFHOME=.;c:\Progra~1\JMF21~1.1

REM set jmapps topdir
set JMAPPSDIR=.

REM set where the compiled classes will go
set TARGDIR=%JMAPPSDIR%\lib

md %TARGDIR%

%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\registry\MimePanel.java 
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\registry\CDMPanel.java 
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\registry\VectorPanel.java 
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\registry\PIMPanel.java 
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\registry\PMPanel.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\registry\SettingsPanel.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\registry\PerTypePanel.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\registry\VectorEditor.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\ui\ImageArea.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\ui\JMDialog.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\ui\JMFrame.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\ui\JMPanel.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\ui\MessageDialog.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\ui\PlayerFrame.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\ui\PopupWait.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\ui\ProgressBar.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\ui\ProgressDialog.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\ui\ProgressThread.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\ui\SnapFrame.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\ui\TextView.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\ui\TrackPanel.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\ui\TrackPanelAudio.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\ui\TrackPanelVideo.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\ui\VideoPanel.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\ui\WizardDialog.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\ui\TreeNode.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\ui\TreeControl.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\jmstudio\CaptureDialog.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\jmstudio\AboutDialog.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\jmstudio\CaptureControlsDialog.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\jmstudio\OpenRtpDialog.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\jmstudio\OpenUrlDialog.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\jmstudio\SaveAsDialog.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\jmstudio\TransmitWizard.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\util\JMAppsCfg.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\util\StateHelper.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\util\JMFUtils.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\util\CDSWrapper.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\export\ExportWizard.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\export\PanelMediaTargetFormat.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\export\PanelMediaSource.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\export\PanelMediaTargetFile.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\export\PanelMediaTargetNetwork.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\export\PanelMediaTargetType.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\export\TransmitPlayerWindow.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\rtp\PanelOverallRtpStats.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\rtp\SessionControlDialog.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\rtp\TransmissionStatsDialog.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\rtp\PanelParticipants.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\rtp\ViewParticipantList.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\rtp\ViewParticipantInfo.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\rtp\ViewReceiverReport.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\rtp\ViewSenderReport.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\rtp\ViewReportBlock.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\rtp\ViewRtpStreamInfo.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\jmapps\rtp\ViewSourceDescription.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\Export.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\JMFRegistry.java
%JAVAHOME%\bin\javac -g -classpath %JMFHOME%\lib\jmf.jar -d %TARGDIR% %JMAPPSDIR%\JMStudio.java
