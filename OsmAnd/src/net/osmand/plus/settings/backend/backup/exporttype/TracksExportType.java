package net.osmand.plus.settings.backend.backup.exporttype;

import static net.osmand.IndexConstants.GPX_INDEX_DIR;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import net.osmand.plus.OsmandApplication;
import net.osmand.plus.R;
import net.osmand.plus.download.local.LocalItemType;
import net.osmand.plus.plugins.OsmandPlugin;
import net.osmand.plus.settings.backend.ExportCategory;
import net.osmand.plus.settings.backend.backup.SettingsItemType;
import net.osmand.plus.settings.backend.backup.items.FileSettingsItem;
import net.osmand.plus.settings.backend.backup.items.FileSettingsItem.FileSubtype;
import net.osmand.plus.settings.backend.backup.items.SettingsItem;
import net.osmand.plus.track.data.GPXInfo;
import net.osmand.plus.track.helpers.GpxUiHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TracksExportType extends AbstractFileExportType {

	@Override
	public int getTitleId() {
		return R.string.shared_string_tracks;
	}

	@Override
	public int getIconId() {
		return R.drawable.ic_action_polygom_dark;
	}

	@NonNull
	@Override
	public List<?> fetchExportData(@NonNull OsmandApplication app, boolean offlineBackup) {
		List<File> files = new ArrayList<>();
		List<GPXInfo> infos = GpxUiHelper.getGPXFiles(app.getAppPath(GPX_INDEX_DIR), true);
		for (GPXInfo info : infos) {
			File file = info.getFile();
			if (file != null && file.exists() && !file.isDirectory()) {
				files.add(file);
			}
		}
		return files;
	}

	@NonNull
	@Override
	public List<?> fetchImportData(@NonNull SettingsItem settingsItem, boolean importCompleted) {
		FileSettingsItem fileSettingsItem = (FileSettingsItem) settingsItem;
		return Collections.singletonList(fileSettingsItem);
	}

	@NonNull
	@Override
	public ExportCategory getRelatedExportCategory() {
		return ExportCategory.MY_PLACES;
	}

	@NonNull
	@Override
	public SettingsItemType getRelatedSettingsItemType() {
		return SettingsItemType.GPX;
	}

	@NonNull
	@Override
	public List<FileSubtype> getRelatedFileSubtypes() {
		return Collections.singletonList(FileSubtype.GPX);
	}

	@Nullable
	@Override
	public LocalItemType getRelatedLocalItemType() {
		return LocalItemType.TRACKS;
	}

	@Nullable
	@Override
	public Class<? extends OsmandPlugin> getRelatedPluginClass() {
		return null;
	}
}
