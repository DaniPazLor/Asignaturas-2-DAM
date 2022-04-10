#!/bin/sh

#
# %W% %E%
# Sample build script for JMStudio, JMFRegistry on Solaris.
#

# Where to get JDK from.  Requires JDK 1.1.* or above.
JAVAHOME=<JDK>

# Where JMF is installed
JMFHOME=<JMF>

# The jmapps toplevel directory.
JMAPPSDIR=.

# Where the compiled classes will be placed.
TARGDIR=${JMAPPSDIR}/lib

export JMAPPSDIR
export TARGDIR
export JAVAHOME
export JMFHOME

/bin/mkdir -p ${TARGDIR}

${JAVAHOME}/bin/javac -g -classpath ${JMFHOME}/lib/jmf.jar \
        -d ${TARGDIR} \
        ${JMAPPSDIR}/jmapps/registry/MimePanel.java \
	${JMAPPSDIR}/jmapps/registry/CDMPanel.java \
	${JMAPPSDIR}/jmapps/registry/VectorPanel.java \
	${JMAPPSDIR}/jmapps/registry/PIMPanel.java \
	${JMAPPSDIR}/jmapps/registry/PMPanel.java \
	${JMAPPSDIR}/jmapps/registry/SettingsPanel.java \
	${JMAPPSDIR}/jmapps/registry/PerTypePanel.java \
	${JMAPPSDIR}/jmapps/registry/VectorEditor.java \
	${JMAPPSDIR}/jmapps/ui/ImageArea.java \
	${JMAPPSDIR}/jmapps/ui/JMDialog.java \
	${JMAPPSDIR}/jmapps/ui/JMFrame.java \
	${JMAPPSDIR}/jmapps/ui/JMPanel.java \
	${JMAPPSDIR}/jmapps/ui/MessageDialog.java \
	${JMAPPSDIR}/jmapps/ui/PlayerFrame.java \
	${JMAPPSDIR}/jmapps/ui/PopupWait.java \
	${JMAPPSDIR}/jmapps/ui/ProgressBar.java \
	${JMAPPSDIR}/jmapps/ui/ProgressDialog.java \
	${JMAPPSDIR}/jmapps/ui/ProgressThread.java \
	${JMAPPSDIR}/jmapps/ui/SnapFrame.java \
	${JMAPPSDIR}/jmapps/ui/TextView.java \
	${JMAPPSDIR}/jmapps/ui/TrackPanel.java \
	${JMAPPSDIR}/jmapps/ui/TrackPanelAudio.java \
	${JMAPPSDIR}/jmapps/ui/TrackPanelVideo.java \
	${JMAPPSDIR}/jmapps/ui/VideoPanel.java \
	${JMAPPSDIR}/jmapps/ui/WizardDialog.java \
	${JMAPPSDIR}/jmapps/ui/TreeNode.java \
	${JMAPPSDIR}/jmapps/ui/TreeControl.java \
	${JMAPPSDIR}/jmapps/jmstudio/CaptureDialog.java \
	${JMAPPSDIR}/jmapps/jmstudio/AboutDialog.java \
	${JMAPPSDIR}/jmapps/jmstudio/CaptureControlsDialog.java \
	${JMAPPSDIR}/jmapps/jmstudio/OpenRtpDialog.java \
	${JMAPPSDIR}/jmapps/jmstudio/OpenUrlDialog.java \
	${JMAPPSDIR}/jmapps/jmstudio/SaveAsDialog.java \
	${JMAPPSDIR}/jmapps/jmstudio/TransmitWizard.java \
	${JMAPPSDIR}/jmapps/util/JMAppsCfg.java \
	${JMAPPSDIR}/jmapps/util/StateHelper.java \
	${JMAPPSDIR}/jmapps/util/JMFUtils.java \
	${JMAPPSDIR}/jmapps/util/CDSWrapper.java \
	${JMAPPSDIR}/jmapps/export/ExportWizard.java \
	${JMAPPSDIR}/jmapps/export/PanelMediaTargetFormat.java \
	${JMAPPSDIR}/jmapps/export/PanelMediaSource.java \
	${JMAPPSDIR}/jmapps/export/PanelMediaTargetFile.java \
	${JMAPPSDIR}/jmapps/export/PanelMediaTargetNetwork.java \
	${JMAPPSDIR}/jmapps/export/PanelMediaTargetType.java \
	${JMAPPSDIR}/jmapps/export/TransmitPlayerWindow.java \
	${JMAPPSDIR}/jmapps/rtp/PanelOverallRtpStats.java \
	${JMAPPSDIR}/jmapps/rtp/SessionControlDialog.java \
	${JMAPPSDIR}/jmapps/rtp/TransmissionStatsDialog.java \
	${JMAPPSDIR}/jmapps/rtp/PanelParticipants.java \
	${JMAPPSDIR}/jmapps/rtp/ViewParticipantList.java \
	${JMAPPSDIR}/jmapps/rtp/ViewParticipantInfo.java \
	${JMAPPSDIR}/jmapps/rtp/ViewReceiverReport.java \
	${JMAPPSDIR}/jmapps/rtp/ViewSenderReport.java \
	${JMAPPSDIR}/jmapps/rtp/ViewReportBlock.java \
	${JMAPPSDIR}/jmapps/rtp/ViewRtpStreamInfo.java \
	${JMAPPSDIR}/jmapps/rtp/ViewSourceDescription.java \
	${JMAPPSDIR}/Export.java \
	${JMAPPSDIR}/JMFRegistry.java \
	${JMAPPSDIR}/JMStudio.java

