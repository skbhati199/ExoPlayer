package com.infoskillstechnology.sinetop.ui.dashboard

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.drm.*
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.upstream.HttpDataSource
import com.google.android.exoplayer2.util.Assertions
import com.google.android.exoplayer2.util.Util
import com.infoskillstechnology.sinetop.R

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var fullScreenView: SurfaceView? = null
    private var currentOutputView: SurfaceView? = null
    private var player: SimpleExoPlayer? = null
    private var videoSurface: Surface? = null

    private var surfaceControl: SurfaceControl? = null


    private val DEFAULT_MEDIA_URI = "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv"
    private val SURFACE_CONTROL_NAME = "surfacedemo"

    private val ACTION_VIEW = "com.google.android.exoplayer.surfacedemo.action.VIEW"
    private val EXTENSION_EXTRA = "extension"
    private val DRM_SCHEME_EXTRA = "drm_scheme"
    private val DRM_LICENSE_URL_EXTRA = "drm_license_url"
    private val OWNER_EXTRA = "owner"

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        fullScreenView = root.findViewById<SurfaceView>(R.id.full_screen_view)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            initializePlayer()

            setCurrentOutputView(fullScreenView)
        }
    }


    private fun setCurrentOutputView(surfaceView: SurfaceView?) {
        currentOutputView = surfaceView
        if (surfaceView != null && surfaceView.holder.surface != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                reparent(surfaceView)
            }
        }
    }


    private fun reparent(surfaceView: SurfaceView?) {
        val surfaceControl = Assertions.checkNotNull<SurfaceControl>(surfaceControl)
        if (surfaceView == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                SurfaceControl.Transaction()
                        .reparent(surfaceControl,  /* newParent= */null)
                        .setBufferSize(surfaceControl,  /* w= */0,  /* h= */0)
                        .setVisibility(surfaceControl,  /* visible= */false)
                        .apply()
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val newParentSurfaceControl = surfaceView.surfaceControl
                SurfaceControl.Transaction()
                        .reparent(surfaceControl, newParentSurfaceControl)
                        .setBufferSize(surfaceControl, surfaceView.width, surfaceView.height)
                        .setVisibility(surfaceControl,  /* visible= */true)
                        .apply()
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    private fun initializePlayer() {
        val uri = Uri.parse(DEFAULT_MEDIA_URI)
        val drmSessionManager  : DrmSessionManager<ExoMediaCrypto> = DrmSessionManager.getDummyDrmSessionManager()
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
                requireContext(), Util.getUserAgent(requireContext(), getString(R.string.app_name)))
        val mediaSource: MediaSource
        mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .setDrmSessionManager(drmSessionManager)
                .createMediaSource(uri)
        var player = SimpleExoPlayer.Builder(requireContext()).build()
        player.prepare(mediaSource)
        player.playWhenReady = true
        player.repeatMode = Player.REPEAT_MODE_ALL
        surfaceControl = SurfaceControl.Builder()
                .setName(SURFACE_CONTROL_NAME)
                .setBufferSize( /* width= */0,  /* height= */0)
                .build()
        videoSurface = Surface(surfaceControl!!)
        player.setVideoSurface(videoSurface)
        this.player = player
    }
}
